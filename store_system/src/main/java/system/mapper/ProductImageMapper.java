package system.mapper;

import org.springframework.beans.factory.annotation.Qualifier;
import system.pojo.ProductImage;

@Qualifier("ProductImageMapper")
public interface ProductImageMapper {
    int insert(ProductImage image);
}
