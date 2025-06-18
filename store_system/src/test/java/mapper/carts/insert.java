package mapper.carts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.CartItemsMapper;
import system.mapper.CartsMapper;
import system.pojo.CartItems;
import system.pojo.Carts;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class insert {


    @Autowired
    @Qualifier("CartsMapper")
    private CartsMapper cartsMapper;

    @Autowired
    @Qualifier("CartItemsMapper")
    private CartItemsMapper cartItemsMapper;
    @Test
    public void insert() {
        cartsMapper.insert(2);
    }
    @Test
    public void insert2() {
        CartItems cartItems = new CartItems();
        cartItems.setCartId(1);
        cartItems.setProductId(2);
        cartItems.setQuantity(1);
        cartItemsMapper.insert(cartItems);
    }
    @Test
    public void select() {
        Carts select = cartsMapper.select(2);
        System.out.println(select);
    }
    @Test
    public void select2() {
        List<CartItems> select = cartItemsMapper.select(0);
        System.out.println(select);
    }

    @Test
    public void delete(){
        cartItemsMapper.delete(3);
    }

    @Test
    public void update() {
        cartItemsMapper.updateQuantity(1,2);
    }
}
