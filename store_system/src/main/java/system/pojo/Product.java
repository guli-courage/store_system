package system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer productStock;
    private Integer productSales;
    private String productDescription;
    private String productState;
    private Integer productStoreId;
    private Integer productTypeId;
    private ProductType productType;
    private List<ProductImage> productImageList;
}
