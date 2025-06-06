package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.Product;

@Repository("ProductMapper")
public interface ProductMapper {
    int updateState(@Param("productState")String productState,@Param("productId")Integer productId);
    int insertProduct(Product product);
    Product selectByNameAndStoreId(Product product);
}
