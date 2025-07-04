package system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.pojo.UserAddress;
import system.vo.OrderItemVo;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String token;
    private List<OrderItemVo> itemsVo;
    private UserAddress userAddress;
}
