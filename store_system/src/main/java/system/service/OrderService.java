package system.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import system.common.RedisKey;
import system.common.Result;
import system.common.exceptions.BusinessException;
import system.mapper.OrderItemMapper;
import system.mapper.OrderMapper;
import system.mapper.ProductMapper;
import system.pojo.Order;
import system.pojo.OrderItem;
import system.pojo.Product;
import system.pojo.dto.OrderDto;

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
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;

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

        return Result.SUCCESS(order); // 返回创建好的订单对象

    }
}
