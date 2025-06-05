package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.Manager;
import system.service.ManagerService;

@RestController
@RequestMapping("manager")
@CrossOrigin
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @PostMapping("/managerLogin")
    public Result login(@RequestBody Manager manager) {
        return managerService.managerLogin(manager);
    }

    @RequestMapping("/searchStore")
    public Result searchStore(){
        return managerService.searchVerifyStore();
    }

    @RequestMapping("/updateStoreState")
    public Result updateStoreState(String storeState,Integer storeId){
        return managerService.updateState(storeState,storeId);
    }


}
