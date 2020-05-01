package me.baocai.adal.web.service.impl;

import me.baocai.adal.web.mapper.RoleDao;
import me.baocai.adal.web.model.Role;
import me.baocai.adal.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"roleCache"})
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<Role> getRolesByUserId(Long userId) {
        return roleDao.getRolesByUserId(userId);
    }
}
