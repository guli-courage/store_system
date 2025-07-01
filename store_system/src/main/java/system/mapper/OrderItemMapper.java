package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.OrderItem;

import java.util.List;

@Repository("OrderItemMapper")
public interface OrderItemMapper {

    /**
     * 插入订单项
     * @param orderItem 订单项对象
     * @return 插入的行数
     */
    int insertOrderItem(OrderItem orderItem);

    /**
     * 根据订单ID查询订单项
     * @param ordersId 订单ID
     * @return 订单项列表
     */
    List<OrderItem> selectByOrderId(@Param("ordersId") Integer ordersId);

    /**
     * 根据订单项ID查询订单项
     * @param orderItemId 订单项ID
     * @return 订单项对象
     */
    OrderItem selectById(@Param("orderItemId") Integer orderItemId);

    /**
     * 根据商品ID查询订单项
     * @param productId 商品ID
     * @return 订单项对象
     */
    OrderItem selectByProductId(@Param("productId")Integer productId);

    /**
     * 更新订单项数量
     * @param orderItemId 订单项ID
     * @param quantity 新数量
     * @return 更新的行数
     */
    int updateQuantity(@Param("orderItemId") Integer orderItemId,
                       @Param("quantity") Integer quantity);

    /**
     * 删除订单项
     * @param orderItemId 订单项ID
     * @return 删除的行数
     */
    int deleteOrderItem(@Param("orderItemId") Integer orderItemId);

    /**
     * 根据订单ID删除所有订单项
     * @param ordersId 订单ID
     * @return 删除的行数
     */
    int deleteByOrderId(@Param("ordersId") Integer ordersId);
}