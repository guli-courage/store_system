package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.Product;
import system.service.ProductService;
import system.vo.ProductVo;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * 商店申请新增商品
     * @param product（新增商品信息）
     */
    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    /**
     * 查询所有的商品类型
     */
    @RequestMapping("/searchAllType")
    public Result searchAllType() {
        return productService.searchAllType();
    }

    /**
     * 多条件查询所有符合条件的商品
     * @param productVo（查询条件）
     */
    @RequestMapping("/searchProductByVo")
    public Result searchProductByVo(@RequestBody ProductVo productVo) {
        return productService.searchProduct(productVo);
    }

    /**
     * 修改商品信息并申请上架
     * @param product （修改后的商品信息）
     */
    @PostMapping("/updateProduct")
    public Result updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    /**
     * 根据商品ID查询商品
     * @param productId 商品ID
     */
    @RequestMapping("/searchProductById")
    public Result searchProductById(Integer productId) {
        return productService.selectProductById(productId);
    }

    /**
     * 商店为商品进货
     * @param productId 商品Id
     * @param newCount 新增的库存量
     */
    @RequestMapping("/updateStock")
    public Result updateStock(Integer productId, Integer newCount) {
        return productService.updateStock(productId, newCount);
    }

    /**
     * 商店修改商品售价
     * @param productId 商品Id
     * @param newPrice 新的商品价格
     */
    @RequestMapping("/updatePrice")
    public Result updatePrice(Integer productId, Double newPrice) {
        return productService.updatePrice(productId,newPrice);
    }

}
