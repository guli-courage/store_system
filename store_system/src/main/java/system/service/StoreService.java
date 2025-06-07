package system.service;

import com.baomidou.mybatisplus.extension.api.R;
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

    /**
     *查询商店
     * @param storeVo（查询条件）
     * @return 返回所有符合条件的商店
     */
    public Result selectAllByVO(StoreVo storeVo) {
        return Result.SUCCESS(storeMapper.selectAll(storeVo));
    }

    /**
     * 修改商店信息并将商店提交至审核态
     * @param store（该商店原本的信息）
     * @return 成功or失败
     */
    public Result update(Store store) {
        if(storeMapper.updateStore(store) !=0){
            return Result.SUCCESS();
        }else {
            return Result.FAIL("修改失败");
        }

    }
}
