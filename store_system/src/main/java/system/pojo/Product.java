package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer productId;
    private String productName;
    private Double productPrice;
    private Integer productStock;
    private Integer productSales;
    private String productDescription;
    private Integer productStoreId;
    private Integer productTypeId;
    private ProductType productType;
    private List<ProductImage> productImageList;
}
