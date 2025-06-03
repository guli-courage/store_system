package system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import system.common.Result;
import system.mapper.ManagerMapper;
import system.pojo.Manager;
import system.utils.MD5Util;

@Service
public class ManagerService {


    @Autowired
    @Qualifier("ManagerMapper")
    private ManagerMapper managerMapper;


    public Result managerLogin(Manager manager) {
        manager.setManagerPassword(MD5Util.MD5ToString(manager.getManagerPassword()));
        Manager m = managerMapper.selectByNameAndPass(manager);
        if (m != null) {
            return Result.SUCCESS(m);
        }else {
            return Result.FAIL();
        }
    }


}
