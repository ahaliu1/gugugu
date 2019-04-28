package cn.whu.gugugu.service;

import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.repository.UserRepository;
import cn.whu.gugugu.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public int updateOrInsertUser(User user) {
        if (userRepository.getUser(user.getOpenId()) == null) {
            user.setAccount(0);
            return userRepository.insertUser(user);
        } else {
            return userRepository.updateUser(user);
        }
    }

    @Override
    public int updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public int insertUser(User user) {
        return userRepository.insertUser(user);
    }

    @Override
    public User getUser(String userId) {
        return userRepository.getUser(userId);
    }
}
