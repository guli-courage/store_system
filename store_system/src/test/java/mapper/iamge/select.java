package mapper.iamge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.ProductImageMapper;
import system.pojo.ProductImage;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class select {

    @Autowired
    @Qualifier("ProductImageMapper")
    private ProductImageMapper productImageMapper;

    @Test
    public void select() {
        List<ProductImage> productImages = productImageMapper.selectByProduct(1);
        productImages.forEach(System.out::println);
    }
}
