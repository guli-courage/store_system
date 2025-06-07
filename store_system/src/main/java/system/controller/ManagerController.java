package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.Manager;
import system.service.ManagerService;
import system.service.ProductService;
import system.service.StoreService;
import system.vo.StoreVo;

@RestController
@RequestMapping("manager")
@CrossOrigin
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StoreService storeService;

    /**
     * 管理员登录
     * @param manager（管理员账号密码）
     */
    @PostMapping("/managerLogin")
    public Result login(@RequestBody Manager manager) {
        return managerService.managerLogin(manager);
    }

    /**
     *查询所有待审核的商店
     */
    @RequestMapping("/searchStore")
    public Result searchStore(){
        return managerService.searchVerifyStore();
    }

    /**
     * 修改审核中商品的状态（上架或者回退）
     * @param storeState（商店状态）
     * @param storeId（商店Id）
     */
    @RequestMapping("/updateStoreState")
    public Result updateStoreState(String storeState,Integer storeId){
        return managerService.updateState(storeState,storeId);
    }

    /**
     * 修改审核中商品状态（上架或者回退）
     * @param productId（商品Id）
     * @param productState（商品状态）
     */
    @RequestMapping("/updateProductState")
    public Result updateState(Integer productId, String productState) {
        return productService.updateState(productState,productId);
    }

    /**
     * 查询该商店所有审核中的商品
     * @param productStoreId（商店Id）
     */
    @RequestMapping("/searchProduct")
    public Result searchProduct(Integer productStoreId){
        return productService.searchVerify(productStoreId);
    }

    /**
     *查询所有开设中的商品
     */
    @RequestMapping("/searchAllStore")
    public Result searchAllStore(){
        StoreVo storeVo = new StoreVo();
        storeVo.setStoreState("verified");
        return storeService.selectAllByVO(storeVo);
    }
}
