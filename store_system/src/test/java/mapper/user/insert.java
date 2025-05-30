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
public class insert {

    @Autowired
    @Qualifier("UserMapper")
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUserName("test");
        user.setUserGender("女");
        user.setUserOpenId("123456");
        userMapper.insert(user);
    }
}
