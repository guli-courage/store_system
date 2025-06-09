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

    /**
     * 根据用户ID查询其所有的商店
     * @param storeUserId（用户ID）
     */
    @RequestMapping("/searchStore")
    public Result searchStore(Integer storeUserId) {
        StoreVo storeVo = new StoreVo();
        storeVo.setStoreUserId(storeUserId);
        return storeService.selectAllByVO(storeVo);
    }

    /**
     * 更新商店信息并进入审核状态
     * @param store（修改后的商店信息）
     */
    @PostMapping("/updateStore")
    public Result updateStore(@RequestBody Store store) {
        return storeService.update(store);
    }
}
