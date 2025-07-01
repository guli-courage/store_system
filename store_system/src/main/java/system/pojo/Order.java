package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer ordersId;
    private Integer ordersUserId;
    private Double ordersAmount;
    private String ordersStatus; // 对应ENUM值
    private Date createdAt;
    private Date updatedAt;
    private List<OrderItem> orderItems;
}
