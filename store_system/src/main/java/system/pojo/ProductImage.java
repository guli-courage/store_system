package system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer ImageId;
    private Integer ImageProductId;
    private String ImageUrl;
}
