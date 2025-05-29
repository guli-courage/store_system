package system.model;

import lombok.Data;

@Data
public class WxUserInfo {
    private String openid;
    private String nickname;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
}
