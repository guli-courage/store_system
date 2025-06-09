package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.ProductType;

import java.util.List;

@Repository("ProductTypeMapper")
public interface ProductTypeMapper {

    List<ProductType> selectAll();
    Integer insert(@Param("typeName")String typeName);
}
