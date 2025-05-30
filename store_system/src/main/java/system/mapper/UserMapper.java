package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.User;

@Repository("UserMapper")
public interface UserMapper {
    User selectByOpenId(@Param("userOpenId")String openId);

    void insert(User user);
}
