package system.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import system.pojo.Store;
import system.vo.StoreVo;

import java.util.List;

@Repository("StoreMapper")
public interface StoreMapper {
    List<Store> selectByState(@Param("storeState") String state);

    void updateState(@Param("storeState") String state, @Param("storeId") Integer id);

    int updateStore(Store store);

    int insertStore(Store store);

    List<Store> selectAll(StoreVo storeVo);
}
