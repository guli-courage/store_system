package system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.model.WXAuth;
import system.pojo.Store;
import system.service.UserService;



@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WXAuth wxAuth) {
        //System.out.println(wxAuth);
        Result result = userService.authLogin(wxAuth);
        //log.info("{返回的信息如下}",result);
        return result;
    }

    @PostMapping("/addStore")
    public Result addStore(@RequestBody Store store) {
        return userService.addStore(store);
    }
}
