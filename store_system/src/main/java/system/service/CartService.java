package system.service;


import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import system.common.RedisKey;
import system.common.Result;
import system.mapper.CartItemsMapper;
import system.mapper.CartsMapper;
import system.mapper.ProductImageMapper;
import system.mapper.ProductMapper;
import system.pojo.CartItems;
import system.pojo.Carts;
import system.pojo.Product;
import system.pojo.dto.CartDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("CartsMapper")
    private CartsMapper cartsMapper;

    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;

    @Autowired
    @Qualifier("ProductImageMapper")
    private ProductImageMapper productImageMapper;

    @Autowired
    @Qualifier("CartItemsMapper")
    private CartItemsMapper cartItemsMapper;

    public Result searchCart(String token) {
        String json = stringRedisTemplate.opsForValue().get(RedisKey.TOKEEN + token);
        JSONObject jsonObject = JSONObject.parseObject(json);
        Integer userId = Integer.valueOf(jsonObject.getString("userId"));
        Carts cart = cartsMapper.select(userId);
        if (cart != null) {
            List<CartItems> cartItemsList = cartItemsMapper.select(cart.getCartId());
            List<Product> productList = new ArrayList<>();
            cartItemsList.forEach(cartItems -> {
                Product product = productMapper.selectById(cartItems.getProductId());
                product.setProductImageList(productImageMapper.selectByProduct(product.getProductId()));
                cartItems.setProduct(product);
            });
            return Result.SUCCESS(cartItemsList);
        }else {
            cartsMapper.insert(userId);
            return Result.SUCCESS(cartItemsMapper.select(0));
        }
    }

    public Result addCart(CartDto cartDto) {
        try {
            String json = stringRedisTemplate.opsForValue().get(RedisKey.TOKEEN + cartDto.getToken());
            JSONObject jsonObject = JSONObject.parseObject(json);
            Integer userId = Integer.valueOf(jsonObject.getString("userId"));
            Carts cart = cartsMapper.select(userId);
            CartItems cartItems = new CartItems();
            cartItems.setProductId(cartDto.getProductId());
            if (cartDto.getQuantity()==null){
                cartItems.setQuantity(1);
            }else {
                cartItems.setQuantity(cartDto.getQuantity());
            }
            if (cart == null) {
                cartsMapper.insert(userId);
                Carts newCart = cartsMapper.select(userId);
                cartItems.setCartId(newCart.getCartId());
            }else {
                List<CartItems> select = cartItemsMapper.select(cart.getCartId());
                for (CartItems items : select) {
                    if (items.getProductId().equals(cartDto.getProductId())) {
                        return Result.FAIL("商品存在购物车");
                    }
                }
                cartItems.setCartId(cart.getCartId());
            }
            cartItemsMapper.insert(cartItems);
            return Result.SUCCESS();
        } catch (NumberFormatException e) {
            return Result.FAIL();
        }
    }

    public Result updateQuantity(Integer quantity, Integer cartItemsId) {
        Integer i = cartItemsMapper.updateQuantity(quantity, cartItemsId);
        if (i > 0) {
            return Result.SUCCESS();
        }
        return Result.FAIL();
    }
}
