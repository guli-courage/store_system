package mapper.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.ProductImageMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class delete {


    @Autowired
    private ProductImageMapper productImageMapper;

    @Test
    public void delete() {
        Integer delete = productImageMapper.delete("134324243");
        System.out.println(delete);
    }

}
