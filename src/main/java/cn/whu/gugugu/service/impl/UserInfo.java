package cn.whu.gugugu.service.impl;

import cn.whu.gugugu.generated.model.User;

public interface UserInfo {
    /**
     * 获取用户信息
     * @return
     */
    public User getUserInfo(String token);
}
