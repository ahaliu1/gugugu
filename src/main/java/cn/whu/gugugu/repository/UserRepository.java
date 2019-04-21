package cn.whu.gugugu.repository;

import cn.whu.gugugu.GuguguConfig;
import cn.whu.gugugu.domain.Code2SessionResponse;
import cn.whu.gugugu.generated.mapper.UserMapper;
import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.generated.model.UserExample;
import cn.whu.gugugu.utils.MD5;
import cn.whu.gugugu.utils.ReadData;
import cn.whu.gugugu.utils.UID;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    UserMapper mapper;

    public void writeUser(Code2SessionResponse response) {
        User user = new User();
        user.setOpenId(response.getOpenid());
        user.setLoginTime(new Date());

        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(response.getOpenid());
        long count = mapper.countByExample(example);

        if (count != 0) {
            mapper.insert(user);
        }
        mapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 更新指定id的token
     * @param openId
     */
    public String refreshToken(String openId) {
        User user = mapper.selectByPrimaryKey(openId);
        //过时
        Date date = new Date();
        if (user.getLoginTime().getTime() + 86400000 > date.getTime()) {
            String token = UID.getUUID();
            user.setLoginTime(date);
            user.setToken(token);
            mapper.updateByPrimaryKeySelective(user);
        }
        return user.getToken();
    }

    public int updateUser(User user) {
        return mapper.updateByPrimaryKeySelective(user);
    }

    public int insertUser(User user) {
        return mapper.insertSelective(user);
    }

    public User getUser(String userId) {
        return mapper.selectByPrimaryKey(userId);
    }
}
