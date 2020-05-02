package me.baocai.adal.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.mapper.UserDao;
import me.baocai.adal.web.model.User;
import me.baocai.adal.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
//    @Cacheable(keyGenerator = "customKeyGenerator")
    public Optional<User> findByUsernameOrEmailOrPhone(String username, String email, String phone) {
        return userDao.findByUsernameOrEmailOrPhone(username, email, phone);
    }

    @Override
//    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<User> findByUsernameIn(List<String> usernameList) {
        return userDao.findByUsernameIn(usernameList);
    }
}
