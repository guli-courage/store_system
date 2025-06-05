package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("ProductMapper")
public interface ProductMapper {
    int updateState(@Param("productState")String productState,@Param("productId")Integer productId);
}
