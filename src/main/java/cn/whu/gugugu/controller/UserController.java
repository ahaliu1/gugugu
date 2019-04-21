package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.domain.BaseResponse;
import cn.whu.gugugu.domain.UserDetailResponse;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.service.UesrService;
import cn.whu.gugugu.service.impl.UserImpl;
import cn.whu.gugugu.utils.FixedPointNumber;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends AuthenticatedController {
    UserImpl userService = new UesrService();

    @RequestMapping(value = "/account/user", method = RequestMethod.POST)
    public BaseResponse user() {

    }

    @RequestMapping(value = "/account/detail", method = RequestMethod.GET)
    public BaseResponse userDetail() {
        User user = userService.getUser(this.getRequestedUser().getOpenId());
        return new BaseResponse("ok",
                new UserDetailResponse(user.getUserName(),
                        user.getHeader(),
                        new FixedPointNumber(user.getAccount()).toString()));
    }
}
