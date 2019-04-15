package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.generated.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController extends AuthenticatedController {
    @RequestMapping("getUserInfo")
    public User getUserInfo() {
        if (this.getRequestedUser() == null) {
            return null;
        }
        return null;
    }
}
