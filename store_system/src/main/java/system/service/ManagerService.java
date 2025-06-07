package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.ManagerMapper;
import system.mapper.StoreMapper;
import system.pojo.Manager;
import system.pojo.Store;
import system.utils.MD5Util;

import java.util.List;

@Service
public class ManagerService {


    @Autowired
    @Qualifier("ManagerMapper")
    private ManagerMapper managerMapper;

    @Autowired
    @Qualifier("StoreMapper")
    private StoreMapper storeMapper;

    /**
     * 管理员登录
     * @param manager
     * @return 成功Or失败
     */
    public Result managerLogin(Manager manager) {
        manager.setManagerPassword(MD5Util.MD5ToString(manager.getManagerPassword()));
        Manager m = managerMapper.selectByNameAndPass(manager);
        if (m != null) {
            return Result.SUCCESS(m);
        }else {
            return Result.FAIL();
        }
    }

    /**
     * 查询所有审核中的商店
     * @return 审核中的商店列表
     */
    public Result searchVerifyStore() {
        List<Store> stores = storeMapper.selectByState("verify");
        return Result.SUCCESS(stores);
    }

    /**
     * 修改商店的状态
     * @param state
     * @param storeId
     * @return 成功Or失败
     */
    public Result updateState(String state,Integer storeId) {
        try {
            storeMapper.updateState(state,storeId);
        } catch (Exception e) {
            return Result.FAIL();
        }
        return Result.SUCCESS();
    }
}
