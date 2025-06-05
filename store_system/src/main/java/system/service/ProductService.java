package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.ProductMapper;

@Service
public class ProductService {
    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;

    public Result updateState(String productState, Integer productId) {

        if (productMapper.updateState(productState, productId) != 0) {
            return Result.SUCCESS();
        }else {
            return Result.FAIL("修改失败");
        }
    }
}
