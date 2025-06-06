package system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.Store;
import system.service.StoreService;
import system.vo.StoreVo;

@RestController
@RequestMapping("store")
@CrossOrigin
public class StoreController {

    @Autowired
    private StoreService storeService;

    @RequestMapping("/searchStore")
    public Result searchStore(Integer storeUserId) {
        StoreVo storeVo = new StoreVo();
        storeVo.setStoreUserId(storeUserId);
        return storeService.selectAllByVO(storeVo);
    }

    @PostMapping("/updateStore")
    public Result updateStore(@RequestBody Store store) {
        return storeService.update(store);
    }
}
