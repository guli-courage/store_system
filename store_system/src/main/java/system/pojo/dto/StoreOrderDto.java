package system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.pojo.OrderAddress;
import system.pojo.OrderItem;
import system.pojo.Product;
import system.pojo.User;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreOrderDto {
    private Product product;
    private OrderAddress orderAddress;
    private OrderItem orderItem;
}
