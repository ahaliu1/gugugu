package cn.whu.gugugu.controller;

import cn.whu.gugugu.generated.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {
    @RequestMapping("getUserInfo")
    public User getUserInfo() {
        return null;
    }
}
