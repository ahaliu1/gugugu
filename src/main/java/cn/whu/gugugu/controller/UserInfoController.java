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
    @Autowired
    UserMapper mapper;

    @RequestMapping("/account/login")
    public User getUserInfo() {
        User user = this.getRequestedUser();
        BaseResponse baseResponse = new BaseResponse("ok", new UserInfoResponse());
        return baseResponse;
    }
}
