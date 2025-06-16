package system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {
//    cart_item_id	int
//    cart_id	int
//    product_id	int
//    quantity	int
    private Integer cartItemId;
    private Integer cartId;
    private Integer productId;
    private Product product;
    private Integer quantity;
}
