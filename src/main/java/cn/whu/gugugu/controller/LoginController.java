package cn.whu.gugugu.controller;

import cn.whu.gugugu.GuguguConfig;
import cn.whu.gugugu.commons.AuthenticatedController;
import cn.whu.gugugu.domain.BaseResponse;
import cn.whu.gugugu.domain.Code2SessionResponse;
import cn.whu.gugugu.domain.TokenResponse;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.service.UserServiceImpl;
import cn.whu.gugugu.service.impl.UserService;
import cn.whu.gugugu.utils.ReadData;
import cn.whu.gugugu.utils.UID;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@RestController
public class LoginController extends AuthenticatedController {
    @Autowired
    UserService userService = new UserServiceImpl();

    /**
     * 登陆，获取token
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
    public BaseResponse login(@RequestParam(value = "code") String code,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "header") String header) {
//        String result = getOpenId(code);
        String result = "result";

        switch (result) {
            case "-1":
                return new BaseResponse("system busy");
            case "40029":
                return new BaseResponse("invalid code");
            case "45011":
                return new BaseResponse("login too frequent");
            case "-2":
                return new BaseResponse("system error");
            default:
                break;
        }

        //不管token是否过期，都新建一个token
        User user = new User();
        user.setOpenId(result);
        user.setLoginTime(new Date());
        user.setToken(UID.getUUID());
        user.setUserName(name);
        user.setHeader(header);
        userService.updateOrInsertUser(user);

        return new BaseResponse("ok", new TokenResponse(user.getToken()));
    }

    @RequestMapping(value = "/account/token", method = RequestMethod.GET)
    public BaseResponse token() {
        User user = this.getRequestedUser();
        Date date = new Date();

        //超时
        if (user.getLoginTime().getTime() + 86400000 > date.getTime()) {
            String token = UID.getUUID();
            User temp = new User();
            temp.setOpenId(user.getOpenId());
            temp.setLoginTime(date);
            temp.setToken(token);
            userService.updateUser(temp);
            return new BaseResponse("ok", new TokenResponse(token));
        }
        return new BaseResponse("ok", new TokenResponse(user.getToken()));
    }

    /**
     * 获取session
     *
     * @param code
     * @return -1	系统繁忙，此时请开发者稍候再试
     * openid	0：请求成功，返回open_id
     * 40029	code 无效
     * 45011	频率限制，每个用户每分钟100次
     * -2   系统出错
     */
    public String getOpenId(String code) {
        //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
        HttpURLConnection conn = null;
        try {
            URL url = new URL(String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",
                    GuguguConfig.APPID, GuguguConfig.SECRET, code));
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                String result = ReadData.readData(conn.getInputStream());
                Gson gson = new Gson();
                Code2SessionResponse codeResp = gson.fromJson(result, Code2SessionResponse.class);
                if (codeResp.getErrcode().equals("0")) {
                    return codeResp.getOpenid();
                }
                return codeResp.getErrcode();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return "-2";
    }
}
