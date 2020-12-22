package me.baocai.adal.web.service.impl;

import me.baocai.adal.web.mapper.PermissionDao;
import me.baocai.adal.web.model.SysPermission;
import me.baocai.adal.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"permissionCache"})
public class PermissionServiceImpl implements SysPermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> getPermissionsByRoleIds(List<Long> ids) {
        return permissionDao.getPermissionsByRoleIds(ids);
    }

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> getPermissionsByRoleId(String roleId) {
        return permissionDao.getPermissionsByRoleId(roleId);
    }
}
