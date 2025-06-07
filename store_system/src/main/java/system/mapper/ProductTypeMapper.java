package system.mapper;

import org.springframework.stereotype.Repository;
import system.pojo.ProductType;

import java.util.List;

@Repository("ProductTypeMapper")
public interface ProductTypeMapper {

    List<ProductType> selectAll();
}
