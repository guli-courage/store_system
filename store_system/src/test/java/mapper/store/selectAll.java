package mapper.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.StoreMapper;
import system.vo.StoreVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class selectAll {

    @Autowired
    @Qualifier("StoreMapper")
    private StoreMapper storeMapper;

    @Test
    public void selectAll() {
        StoreVo vo = new StoreVo();
        vo.setStoreName("时分");
        storeMapper.selectAll(vo).forEach(System.out::println);
    }
}
