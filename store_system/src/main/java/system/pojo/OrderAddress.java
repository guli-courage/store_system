package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddress {
    private Integer orderAddressId;   // 地址ID
    private Integer ordersId;        // 关联订单ID
    private String receiverName;     // 收件人姓名
    private String receiverPhone;    // 收件人电话
    private String province;         // 省份
    private String city;             // 城市
    private String district;         // 区
    private String detailAddress;    // 详细地址
}