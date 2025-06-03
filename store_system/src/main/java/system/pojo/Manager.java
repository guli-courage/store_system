package system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {

    @TableId(value = "id" ,type = IdType.AUTO)
    private Integer managerId;

    private String managerName;
    private String managerPassword;
}
