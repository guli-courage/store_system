package system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.pojo.Manager;
import system.service.ManagerService;
import system.service.ProductService;

@RestController
@RequestMapping("manager")
@CrossOrigin
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    @Autowired
    private ProductService productService;



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

    @RequestMapping("/updateProductState")
    public Result updateState(Integer productId, String productState) {
        return productService.updateState(productState,productId);
    }

    @RequestMapping("/searchProduct")
    public Result searchProduct(Integer productStoreId){
        return productService.searchVerify(productStoreId);
    }

}
