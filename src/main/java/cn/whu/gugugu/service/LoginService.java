package cn.whu.gugugu.service;

import cn.whu.gugugu.GuguguConfig;
import cn.whu.gugugu.domain.Code2SessionResponse;
import cn.whu.gugugu.repository.UserRepository;
import cn.whu.gugugu.service.impl.LoginImpl;
import cn.whu.gugugu.utils.ReadData;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class LoginService implements LoginImpl {
    UserRepository userRepository = new UserRepository();

    /**
     * 获取session
     * @param code
     * @return -1	系统繁忙，此时请开发者稍候再试
     *         0	请求成功
     *         40029	code 无效
     *         45011	频率限制，每个用户每分钟100次
     *         -2   系统出错
     */
    @Override
    public String getSession(String code) {
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
                    userRepository.writeUser(codeResp);
                    return codeResp.getErrcode();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return "-2";
    }

    @Override
    public String getToken(String openId) {
        return userRepository.refreshToken(openId);
    }
}
