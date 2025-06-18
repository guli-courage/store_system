package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.dto.CartDto;
import system.pojo.dto.UserDto;
import system.service.CartService;
import system.service.ProductService;

@RestController
@RequestMapping("cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    /**
     * 根据token查询购物车
     * @param userDto 只要回传token
     * @return 购物车列表
     */
    @PostMapping("/searchCart")
    public Result searchCart(@RequestBody UserDto userDto) {
        return cartService.searchCart(userDto.getToken());
    }

    @PostMapping("/addCart")
    public Result addCart(@RequestBody CartDto cartDto) {
        return cartService.addCart(cartDto);
    }

    @RequestMapping ("/updateCart")
    public Result updateCart(Integer quantity,Integer cartItemsId) {
        return cartService.updateQuantity(quantity,cartItemsId);
    }
}
