package mapper.store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.StoreMapper;
import system.pojo.Store;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class updateStore {

    @Autowired
    private StoreMapper storeMapper;

    @Test
    public void updateStore() {
        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("胖嘟来");
        store.setStoreAddress("china");
        store.setStoreAvatar("12345.jpg");
        store.setStoreDescription("为你而服务");
        storeMapper.updateStore(store);
    }
}
