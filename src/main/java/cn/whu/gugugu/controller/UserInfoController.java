package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.domain.BaseResponse;
import cn.whu.gugugu.domain.UserInfoResponse;
import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController extends AuthenticatedController {

    @RequestMapping("/account/login")
    public BaseResponse getUserInfo() {
        User user = this.getRequestedUser();
        return new BaseResponse("ok", new UserInfoResponse(user.getUserName(), user.getHeader(), user.getAccount()));
    }
}
