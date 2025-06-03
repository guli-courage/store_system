package mapper.manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.ManagerMapper;
import system.pojo.Manager;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class select {

    @Autowired
    @Qualifier("ManagerMapper")
    private ManagerMapper managerMapper;

    @Test
    public void select() {
        Manager manager = new Manager();
        manager.setManagerName("Manager");
        manager.setManagerPassword("123456");
        Manager select = managerMapper.selectByNameAndPass(manager);
        System.out.println(select);
    }
}
