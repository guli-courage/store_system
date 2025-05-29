package system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system.common.Result;
import system.model.WXAuth;
import system.service.LoginService;



@RestController
@RequestMapping("user")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("getSessionId")
    public Result getSessionId(String code){
        return loginService.getSessionId(code);
    }

    @PostMapping("/authLogin")
    public Result authLogin(@RequestBody WXAuth wxAuth) {
        Result result = loginService.authLogin(wxAuth);
        log.info("{}",result);
        return result;
    }
}
