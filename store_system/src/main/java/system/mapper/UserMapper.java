package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.User;

@Repository("UserMapper")
public interface UserMapper {
    User selectByOpenId(@Param("userOpenId")String openId);

    User selectById(@Param("userId")Integer userId);

    User selectByNameAndPassword(@Param("userName")String userName,@Param("userPassword")String userPassword);

    void insert(User user);

    Integer updateUserName(@Param("userName") String name,@Param("userId") Integer userId);

    Integer updateUserPassword(@Param("userPassword") String userPassword,@Param("userId") Integer userId);
}
