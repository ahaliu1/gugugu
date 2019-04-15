package cn.whu.gugugu.controller;

import cn.whu.gugugu.generated.mapper.PartyMapper;
import cn.whu.gugugu.generated.model.PartyExample;
import cn.whu.gugugu.service.TokenService;
import cn.whu.gugugu.service.impl.TokenImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
    TokenImpl token = new TokenService();

//    @Autowired
//    PartyMapper mapper;

    @RequestMapping(value = "/account/login", method = RequestMethod.POST)
    public String token(@RequestParam(value = "code") String code) {
//        PartyExample example = new PartyExample();
//        example.createCriteria().andDepositEqualTo(1);
//        mapper.selectByExample(example);
        return token.getToken(token.getSession(code));
    }
}
