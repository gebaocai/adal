package me.baocai.adal.web.service.impl;

import me.baocai.adal.web.mapper.SysRoleDao;
import me.baocai.adal.web.model.SysRole;
import me.baocai.adal.web.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"roleCache"})
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysRole> getRolesByUserId(String userId) {
        return sysRoleDao.getRolesByUserId(userId);
    }
}
