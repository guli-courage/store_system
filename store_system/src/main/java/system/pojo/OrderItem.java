package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Integer orderItemId;
    private Integer ordersId;
    private Integer productId;
    private Double unitPrice;
    private Integer quantity;
}
