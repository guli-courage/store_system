package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.OrderAddress;

@Repository("OrderAddressMapper")
public interface OrderAddressMapper {

    /**
     * 插入订单地址
     * @param orderAddress 地址对象
     * @return 插入的行数
     */
    int insertOrderAddress(OrderAddress orderAddress);

    /**
     * 根据地址ID查询地址
     * @param orderAddressId 地址ID
     * @return 地址对象
     */
    OrderAddress selectById(@Param("orderAddressId") Integer orderAddressId);

    /**
     * 根据订单ID查询地址
     * @param ordersId 订单ID
     * @return 地址对象
     */
    OrderAddress selectByOrderId(@Param("ordersId") Integer ordersId);

    /**
     * 更新订单地址
     * @param orderAddress 地址对象
     * @return 更新的行数
     */
    int updateOrderAddress(OrderAddress orderAddress);

    /**
     * 根据订单ID删除地址
     * @param ordersId 订单ID
     * @return 删除的行数
     */
    int deleteByOrderId(@Param("ordersId") Integer ordersId);

    /**
     * 根据地址ID删除地址
     * @param orderAddressId 地址ID
     * @return 删除的行数
     */
    int deleteById(@Param("orderAddressId") Integer orderAddressId);
}