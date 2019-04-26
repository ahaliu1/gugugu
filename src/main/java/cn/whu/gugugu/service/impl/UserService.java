package cn.whu.gugugu.service.impl;

import cn.whu.gugugu.generated.model.User;

public interface UserService {
    public int updateOrInsertUser(User user);

    public int updateUser(User user);

    public int insertUser(User user);

    public User getUser(String userId);
}
