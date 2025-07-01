package system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.model.WXAuth;
import system.pojo.Store;
import system.pojo.dto.UserDto;
import system.service.UserService;


@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 微信一键授权登录
     *
     * @param wxAuth（微信数据）
     */
    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WXAuth wxAuth) {
        //System.out.println("前端数据如下"+wxAuth);
        Result result = userService.authLogin(wxAuth);
        //log.info("{返回的信息如下}",result);
        return result;
    }

    /**
     * 用户商店创建
     *
     * @param store（用户填写的商店信息）
     */
    @PostMapping("/addStore")
    public Result addStore(@RequestBody Store store) {
        return userService.addStore(store);
    }

    /**
     * 用户设置用户名
     * @param userDto 主要填写用户姓名和用户识别号
     */
    @PostMapping("/updateName")
    public Result updateName(@RequestBody UserDto userDto) {
        return userService.updateUserName(userDto.getUserName(), userDto.getToken());
    }

    /**
     * 用户设置密码
     * @param userDto 主要填写用户密码和用户识别号
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody UserDto userDto) {
        return userService.updatePassword(userDto.getUserPassword(),userDto.getToken());
    }

    /**
     * 用户用账号密码登录
     * @param userName 账号
     * @param userPassword 密码
     */
    @RequestMapping("/loginByName")
    public Result loginByName(String userName,String userPassword) {
        return userService.loginByName(userName,userPassword);
    }
}
