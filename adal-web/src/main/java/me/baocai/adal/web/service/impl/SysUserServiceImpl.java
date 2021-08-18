package me.baocai.adal.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysUser;
import me.baocai.adal.web.mapper.SysUserDao;
import me.baocai.adal.web.service.SysUserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"userCache"})
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public Optional<SysUser> findByUsernameOrPhone(String username, String phone) {
        return baseMapper.findByUsernameOrPhone(username, phone);
    }

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysUser> findByUsernameIn(List<String> usernameList) {
        return baseMapper.findByUsernameIn(usernameList);
    }
}
