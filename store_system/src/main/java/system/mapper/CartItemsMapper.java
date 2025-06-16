package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.CartItems;

import java.util.List;

@Repository("CartItemsMapper")
public interface CartItemsMapper {
    /**
     * 添加购物车商品
     * @param cartItems 只需要购物车Id和商品Id以及商品数量（大于0）
     * @return 修改数量
     */
    Integer insert(CartItems cartItems);

    /**
     * 先通过用户Id查询购物车id，再查出所有的购物车商品
     * @param cartId 购物车id
     * @return 购物车列表队列，再通过其中的商品id查询商品
     */
    List<CartItems> select(@Param("cartId")Integer cartId);

    /**
     * 删除购物车
     * @param cartItemId 购物车商品表
     * @return 修改数量
     */
    Integer delete(@Param("cartItemId")Integer cartItemId);

    /**
     * 修改购物车的商品的商品数量
     * @param quantity 商品数量
     * @param cartItemId 购物车商品表id
     * @return 修改数量
     */
    Integer updateQuantity(@Param("quantity") Integer quantity, @Param("cartItemId")Integer cartItemId);
}
