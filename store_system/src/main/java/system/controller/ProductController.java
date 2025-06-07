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
}
