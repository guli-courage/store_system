package mapper.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.ProductMapper;
import system.pojo.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class update {


    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;
    @Test
    public void update(){
       Product product = new Product();
       product.setProductName("哇哈哈");
       product.setProductPrice(5.0);
       product.setProductStock(100);
       product.setProductSales(15);
       product.setProductDescription("甜牛奶");
       product.setProductState("verify");
       product.setProductTypeId(1);
       product.setProductId(6);
       productMapper.updateProduct(product);
    }
}
