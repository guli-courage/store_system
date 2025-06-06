package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import system.common.Result;
import system.pojo.Product;
import system.pojo.ProductImage;
import system.service.ProductService;

@RestController
@RequestMapping("product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public Result addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
