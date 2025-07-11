package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.Product;
import system.vo.ProductVo;

import java.util.List;

@Repository("ProductMapper")
public interface ProductMapper {
    int updateState(@Param("productState")String productState,@Param("productId")Integer productId);
    int updateStock(@Param("productStock")Integer productStock,@Param("productId")Integer productId);
    int updatePrice(@Param("productPrice")Double productPrice,@Param("productId")Integer productId);
    int updateSales(@Param("productSales")Integer productSales,@Param("productId")Integer productId);
    int insertProduct(Product product);
    int updateProduct(Product product);
    Product selectByNameAndStoreId(Product product);
    List<Product> selectByVo(ProductVo productVo);
    List<Product> selectOrder(ProductVo productVo);
    Product selectById(@Param("productId")Integer productId);
}
