package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.Carts;

@Repository("CartsMapper")
public interface CartsMapper {
    Integer insert(@Param("userId") Integer userId);

    Carts select(@Param("userId") Integer userId);
}
