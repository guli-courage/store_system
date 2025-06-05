package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType {
//    type_id	int
//    type_name	varchar
    private Integer typeId;
    private String typeName;
}
