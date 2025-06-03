package mapper.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.StoreMapper;
import system.pojo.Store;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class selectByState {

    @Autowired
    @Qualifier("StoreMapper")
    private StoreMapper storeMapper;

    @Test
    public void selectByState() {
        List<Store> stores = storeMapper.selectByState("1");
        for (Store store : stores) {
            System.out.println(store);
        }
    }
}
