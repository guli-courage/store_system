package system.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import system.common.RedisKey;
import system.common.Result;
import system.common.exceptions.BusinessException;
import system.mapper.*;
import system.pojo.*;
import system.pojo.dto.OrderDto;
import system.pojo.dto.StoreOrderDto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class OrderService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("OrderMapper")
    private OrderMapper orderMapper;

    @Autowired
    @Qualifier("OrderItemMapper")
    private OrderItemMapper orderItemMapper;

    @Autowired
    @Qualifier("OrderAddressMapper")
    private OrderAddressMapper orderAddressMapper;

    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;

    @Autowired
    @Qualifier("UserMapper")
    private UserMapper userMapper;

    @Autowired
    @Qualifier("ProductImageMapper")
    private ProductImageMapper productImageMapper;

    public Result addOrder(OrderDto orderDto) {
        Order order = new Order();
        String json = stringRedisTemplate.opsForValue().get(RedisKey.TOKEEN + orderDto.getToken());
        JSONObject jsonObject = JSON.parseObject(json);
        Integer userId = Integer.valueOf(jsonObject.getString("userId"));
        AtomicReference<Double> totalAmount = new AtomicReference<>(0.0);

        // 先计算总金额（不修改库存）
        orderDto.getItemsVo().forEach(itemVo -> {
            Product product = productMapper.selectById(itemVo.getProductId());
            if (product == null) {
                throw new BusinessException("商品不存在: " + itemVo.getProductId());
            }
            // 累加每个订单项的小计
            totalAmount.updateAndGet(v -> v + (product.getProductPrice() * itemVo.getQuantity()));
        });

        // 2. 创建订单主记录
        order.setOrdersUserId(userId);
        order.setOrdersAmount(totalAmount.get()); // 设置计算好的总金额
        order.setOrdersStatus("pending");

        // 插入订单（会返回生成的订单ID）
        orderMapper.insertOrder(order);

        // 3. 处理每个订单项并更新库存
        orderDto.getItemsVo().forEach(itemVo -> {
            Product product = productMapper.selectById(itemVo.getProductId());

            // 检查库存
            if (product.getProductStock() < itemVo.getQuantity()) {
                throw new BusinessException("商品库存不足: " + product.getProductName());
            }

            // 创建订单项
            OrderItem orderItem = new OrderItem();
            orderItem.setOrdersId(order.getOrdersId());
            orderItem.setProductId(itemVo.getProductId());
            orderItem.setUnitPrice(product.getProductPrice());
            orderItem.setQuantity(itemVo.getQuantity());
            orderItemMapper.insertOrderItem(orderItem);

            // 扣减库存
            int newStock = product.getProductStock() - itemVo.getQuantity();
            productMapper.updateStock(newStock, product.getProductId());

            // 增加销量
            int newSales = product.getProductSales() + itemVo.getQuantity();
            productMapper.updateSales(newSales, product.getProductId());
        });
        //储存订单地址
        UserAddress userAddress = orderDto.getUserAddress();
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(userAddress, orderAddress);

        // 处理特殊字段
        orderAddress.setOrdersId(order.getOrdersId());  // 设置订单ID
        orderAddress.setOrderAddressId(null); // 重置ID（数据库自增）
        orderAddressMapper.insertOrderAddress(orderAddress);

        order.setOrderAddress(orderAddress);
        return Result.SUCCESS(order); // 返回创建好的订单对象
    }

    public Result updateOrder(Integer ordersId,String ordersStatus) {
        if (orderMapper.updateOrderStatus(ordersId, ordersStatus)>0){
            return Result.SUCCESS();
        }else {
            return Result.FAIL();
        }

    }

    public Result searchByUser(OrderDto orderDto) {
        String json = stringRedisTemplate.opsForValue().get(RedisKey.TOKEEN + orderDto.getToken());
        JSONObject jsonObject = JSON.parseObject(json);
        Integer userId = Integer.valueOf(jsonObject.getString("userId"));
        List<Order> orders = orderMapper.selectByUserId(userId);
        orders.forEach(order -> {
            List<OrderItem> orderItems = orderItemMapper.selectByOrderId(order.getOrdersId());
            orderItems.forEach(orderItem -> {
                Product product = productMapper.selectById(orderItem.getProductId());
                product.setProductImageList(productImageMapper.selectByProduct(product.getProductId()));
                orderItem.setProduct(product);
            });
            order.setOrderItems(orderItems);
        });
        return Result.SUCCESS(orders);
    }

    public Result searchByProductId(Integer productId) {
        List<OrderItem> orderItems = orderItemMapper.selectByProductId(productId);
        List<StoreOrderDto> storeOrderDtoList = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            //获取订单总表信息
            Order order = orderMapper.selectById(orderItem.getOrdersId());
            StoreOrderDto storeOrderDto = new StoreOrderDto();
            storeOrderDto.setOrderItem(orderItem);
            //查询商品
            Product product = productMapper.selectById(orderItem.getProductId());
            product.setProductPrice(orderItem.getUnitPrice());
            product.setProductImageList(productImageMapper.selectByProduct(product.getProductId()));
            storeOrderDto.setProduct(product);
            //查询订单地址信息
            OrderAddress orderAddress = orderAddressMapper.selectByOrderId(order.getOrdersId());
            storeOrderDto.setOrderAddress(orderAddress);
            storeOrderDtoList.add(storeOrderDto);
        });
        return Result.SUCCESS(storeOrderDtoList);
    }
}
