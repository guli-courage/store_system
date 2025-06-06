package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.ProductImageMapper;
import system.mapper.ProductMapper;
import system.pojo.Product;
import system.pojo.ProductImage;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;

    @Autowired
    @Qualifier("ProductImageMapper")
    private ProductImageMapper productImageMapper;
    public Result updateState(String productState, Integer productId) {

        if (productMapper.updateState(productState, productId) != 0) {
            return Result.SUCCESS();
        }else {
            return Result.FAIL("修改失败");
        }
    }

    public Result addProduct(Product product) {
        if (productMapper.selectByNameAndStoreId(product) == null) {
            if (productMapper.insertProduct(product) != 0) {
                List<ProductImage> productImageList = product.getProductImageList();
                Product newProduct = productMapper.selectByNameAndStoreId(product);
                productImageList.forEach(productImage->{
                    productImage.setImageProductId(newProduct.getProductId());
                    productImageMapper.insert(productImage);
                });
                return Result.SUCCESS();
            }else {
                return Result.FAIL();
            }
        }else {
            return Result.FAIL("商品已经存在");
        }

    }
}
