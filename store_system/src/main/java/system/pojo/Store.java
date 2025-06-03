package system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer storeId;
    private String storeName;
    private String storeAvatar;
    private String storeState;
    private String storeDescription;
    private String storeAddress;
    private Integer storeUserId;
}
