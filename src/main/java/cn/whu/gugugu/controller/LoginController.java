package cn.whu.gugugu.controller;

import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.domain.BaseResponse;
import cn.whu.gugugu.domain.TokenResponse;
import cn.whu.gugugu.service.TokenService;
import cn.whu.gugugu.service.impl.TokenImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends AuthenticatedController {
    TokenImpl tokenService = new TokenService();

    /**
     * -1	系统繁忙，此时请开发者稍候再试
     * 0	请求成功
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     * -2   系统出错
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public BaseResponse login(@RequestParam(value = "code") String code) {
        BaseResponse baseResponse;

        String result = tokenService.getSession(code);
        switch (result) {
            case "0":
                String token = tokenService.getToken(this.getRequestedUser().getOpenId());
                baseResponse = new BaseResponse("ok", new TokenResponse(token));
                break;
            case "-1":
                baseResponse = new BaseResponse("system busy", null);
                break;
            case "40029":
                baseResponse = new BaseResponse("invalid code", null);
                break;
            case "45011":
                baseResponse = new BaseResponse("login too frequent", null);
                break;
            case "-2":
            default:
                baseResponse = new BaseResponse("system error", null);
                break;
        }
        return baseResponse;
    }

    @RequestMapping(value = "/account/token", method = RequestMethod.GET)
    public BaseResponse token() {
        return new BaseResponse("ok", new TokenResponse(tokenService.getToken(this.getRequestedUser().getOpenId())));
    }
}
