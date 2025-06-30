package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.Order;


import java.util.List;

@Repository("OrderMapper")
public interface OrderMapper {

    /**
     * 插入新订单
     * @param order 订单对象
     * @return 插入的行数
     */
    int insertOrder(Order order);

    /**
     * 更新订单状态
     * @param ordersId 订单ID
     * @param ordersStatus 新状态
     * @return 更新的行数
     */
    int updateOrderStatus(@Param("ordersId") Integer ordersId,
                          @Param("ordersStatus") String ordersStatus);

    /**
     * 根据ID查询订单
     * @param ordersId 订单ID
     * @return 订单对象
     */
    Order selectById(@Param("ordersId") Integer ordersId);

    /**
     * 根据用户ID查询订单
     * @param userId 用户ID
     * @return 订单列表
     */
    List<Order> selectByUserId(@Param("userId") Integer userId);

    /**
     * 根据状态查询订单
     * @param status 订单状态
     * @return 订单列表
     */
    List<Order> selectByStatus(@Param("status") String status);

    /**
     * 更新订单金额
     * @param ordersId 订单ID
     * @param ordersAmount 新金额
     * @return 更新的行数
     */
    int updateOrderAmount(@Param("ordersId") Integer ordersId,
                          @Param("ordersAmount") Double ordersAmount);

    /**
     * 删除订单
     * @param ordersId 订单ID
     * @return 删除的行数
     */
    int deleteOrder(@Param("ordersId") Integer ordersId);
}