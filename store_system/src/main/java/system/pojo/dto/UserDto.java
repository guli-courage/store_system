package system.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import system.model.WxUserInfo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userGender;
    private String userOpenId;
    private String userNickname;
    private String userAvatar;
    private String userUnionId;
    private String token;
    private String code;

    public void from(WxUserInfo wxUserInfo) {
        this.userName ="";
        this.userPassword ="";
        this.userGender = wxUserInfo.getGender();
        this.userOpenId = wxUserInfo.getOpenid();
        this.userNickname = wxUserInfo.getNickName();
        this.userAvatar = wxUserInfo.getAvatarUrl();
        this.userUnionId = wxUserInfo.getUnionId();

    }
}
