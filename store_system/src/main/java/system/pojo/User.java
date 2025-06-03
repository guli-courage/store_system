package system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userGender;
    private String userOpenId;
    private String userNickname;
    private String userAvatar;
    private String userUnionId;

}
