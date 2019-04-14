package cn.whu.gugugu.repository;

import cn.whu.gugugu.GuguguConfig;
import cn.whu.gugugu.domain.Code2SessionResponse;
import cn.whu.gugugu.utils.MD5;
import cn.whu.gugugu.utils.ReadData;
import com.google.gson.Gson;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TokenRepository {
    /**
     * 获取session
     *
     * @param code
     * @return
     * @throws IOException
     */
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
                    return codeResp.getSession_key();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return null;
    }

    /**
     * 将session转换为md5加密后的token
     * @param session
     * @return
     */
    public String getToken(String session) {
        //todo 协商token的生成方式
        return MD5.md5(session);
    }
}
