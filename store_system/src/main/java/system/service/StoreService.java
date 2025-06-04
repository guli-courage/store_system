package system.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.StoreMapper;
import system.pojo.Store;
import system.vo.StoreVo;

@Service
public class StoreService {

    @Autowired
    @Qualifier("StoreMapper")
    private StoreMapper storeMapper;

    public Result selectAll(StoreVo storeVo) {
        return Result.SUCCESS(storeMapper.selectAll(storeVo));
    }

    public Result update(Store store) {
        if(storeMapper.updateStore(store) !=0){
            return Result.SUCCESS();
        }else {
            return Result.FAIL("修改失败");
        }

    }
}
