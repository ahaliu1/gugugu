package cn.whu.gugugu.controller;

import cn.whu.gugugu.service.TokenService;
import cn.whu.gugugu.service.impl.TokenImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    TokenImpl token = new TokenService();

    @RequestMapping("/getToken")
    public String token(String code) {
        return token.getToken(token.getSession(code));
    }
}
