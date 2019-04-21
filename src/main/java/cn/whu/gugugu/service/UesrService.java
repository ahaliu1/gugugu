package cn.whu.gugugu.service;

import cn.whu.gugugu.generated.model.User;
import cn.whu.gugugu.repository.UserRepository;
import cn.whu.gugugu.service.impl.UserImpl;
import org.springframework.stereotype.Service;

@Service
public class UesrService implements UserImpl {
    UserRepository userRepository = new UserRepository();

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
