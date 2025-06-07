package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.ProductImageMapper;
import system.mapper.ProductMapper;
import system.mapper.ProductTypeMapper;
import system.pojo.Product;
import system.pojo.ProductImage;
import system.vo.ProductVo;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;

    @Autowired
    @Qualifier("ProductImageMapper")
    private ProductImageMapper productImageMapper;

    @Autowired
    @Qualifier("ProductTypeMapper")
    private ProductTypeMapper productTypeMapper;

    /**
     * 根据商品的Id修改商品的状态
     * @param productState
     * @param productId
     * @return 成功or失败
     */
    public Result updateState(String productState, Integer productId) {

        if (productMapper.updateState(productState, productId) != 0) {
            return Result.SUCCESS();
        }else {
            return Result.FAIL("修改失败");
        }
    }

    /**
     * 用户填写商品信息，并向管理员申请上架该商品
     * @param product
     * @return 如果商品已经存在，返回错误
     */
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

    /**
     * 根据商店ID查询全部审核中的商品
     * @param storeId
     * @return 返回该商店所有审核中的商品
     */
    public Result searchVerify(Integer storeId) {
        ProductVo productVo = new ProductVo();
        productVo.setProductState("verify");
        productVo.setProductStoreId(storeId);
        List<Product> products = productMapper.selectByVo(productVo);
        products.forEach(product->{
            List<ProductImage> productImages = productImageMapper.selectByProduct(product.getProductId());
            product.setProductImageList(productImages);
        });
        return Result.SUCCESS(products);
    }

    /**
     * 根据查询条件查询所有商品
     * @param productVo
     * @return 返回所有的商品并根据销量进行倒续排行
     */
    public Result searchProduct(ProductVo productVo) {
        return Result.SUCCESS(productMapper.selectOrder(productVo));
    }

    public Result searchAllType(){
        return Result.SUCCESS(productTypeMapper.selectAll());
    }
}
