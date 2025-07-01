package system.controller;


import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.dto.OrderDto;
import system.service.OrderService;

@RestController
@RequestMapping("order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody OrderDto orderDto) {
        return orderService.addOrder(orderDto);
    }

    @RequestMapping("/updateOrder")
    public Result updateOrder(Integer orderId,String orderStatus) {
        return orderService.updateOrder(orderId,orderStatus);
    }

    @PostMapping("/searchByUser")
    public Result searchOrder(@RequestBody OrderDto orderDto) {
        return orderService.searchByUser(orderDto);
    }

    @RequestMapping("/searchStoreOrder")
    public Result searchStoreOrder(Integer productId) {
        return orderService.searchByProductId(productId);
    }
}
