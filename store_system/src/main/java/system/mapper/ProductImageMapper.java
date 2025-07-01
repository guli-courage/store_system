package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import system.pojo.ProductImage;

import java.util.List;

@Repository("ProductImageMapper")
public interface ProductImageMapper {
    int insert(ProductImage image);
    List<ProductImage> selectByProduct(@Param("imageProductId")Integer productId);
    Integer delete(@Param("imageUrl")String imageUrl);
}
