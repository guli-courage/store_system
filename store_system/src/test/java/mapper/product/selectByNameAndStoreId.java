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
public class selectByNameAndStoreId {

    @Autowired
    @Qualifier("ProductMapper")
    private ProductMapper productMapper;
    @Test
    public void selectByNameAndStoreId(){
        Product product = new Product();
        product.setProductStoreId(1);
        product.setProductName("可乐");
        System.out.println(productMapper.selectByNameAndStoreId(product));
    }
}
