package mapper.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import system.DoApp;
import system.mapper.UserMapper;
import system.pojo.User;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DoApp.class)
public class select {

    @Autowired
    @Qualifier("UserMapper")
    private UserMapper userMapper;
    @Test
    public void select() {
        User user = userMapper.selectByOpenId("oTWTV5WlWF2-v6ALH5-7TOu3KMgc");
        System.out.println(user);
    }
}
