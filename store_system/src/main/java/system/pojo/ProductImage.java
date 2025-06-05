package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {
    private Integer ImageId;
    private Integer ImageProductId;
    private String ImageUrl;
}
