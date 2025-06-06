package system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {
    private String productName;
    private Double productPrice;
    private Integer productStock;
    private Integer productSales;
    private String productState;
    private Integer productStoreId;
    private Integer productTypeId;
}
