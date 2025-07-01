package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    private Integer userAddressId;   // 用户地址ID
    private Integer userId;          // 关联用户ID
    private String receiverName;     // 收件人姓名
    private String receiverPhone;    // 收件人电话
    private String province;         // 省份
    private String city;             // 城市
    private String district;         // 区县
    private String detailAddress;    // 详细地址
    private Boolean isDefault;       // 是否默认地址
}