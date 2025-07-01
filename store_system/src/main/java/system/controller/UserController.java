package system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.model.WXAuth;
import system.pojo.Store;
import system.pojo.UserAddress;
import system.pojo.dto.OrderDto;
import system.pojo.dto.UserAddressDto;
import system.pojo.dto.UserDto;
import system.service.UserAddressService;
import system.service.UserService;


@RestController
@RequestMapping("user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserAddressService userAddressService;

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

    @PostMapping("/addAddress")
    public Result addAddress(@RequestBody OrderDto orderDto) {
        return userAddressService.insert(orderDto);
    }

    @PostMapping("/setDefault")
    public Result setDefault(@RequestBody UserAddressDto userAddressDto) {
        return userAddressService.setDefaultAddress(userAddressDto.getToken(), userAddressDto.getUserAddress().getUserId());
    }

    @PostMapping("/searchAllAddress")
    public Result searchAllAddress(@RequestBody UserAddressDto userAddressDto) {
        return userAddressService.selectAllByUserId(userAddressDto.getToken());
    }

    @PostMapping("/searchDefaultAddress")
    public Result searchDefaultAddress(@RequestBody UserAddressDto userAddressDto) {
        return userAddressService.selectDefaultByUserId(userAddressDto.getToken());
    }

    @PostMapping("/updateAddress")
    public Result updateAddress(@RequestBody UserAddressDto userAddressDto) {
        return userAddressService.update(userAddressDto.getUserAddress());
    }

    @PostMapping("/deleteAddress")
    public Result deleteAddress(@RequestBody UserAddressDto userAddressDto) {
        return userAddressService.delete(userAddressDto.getUserAddress().getUserAddressId());
    }
}
