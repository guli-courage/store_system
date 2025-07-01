package system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.pojo.UserAddress;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddressDto {
    private String token;
    private UserAddress userAddress;
}
