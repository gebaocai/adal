package me.baocai.adal.web.service.impl;

import me.baocai.adal.web.mapper.PermissionDao;
import me.baocai.adal.web.model.Permission;
import me.baocai.adal.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"permissionCache"})
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<Permission> getPermissionsByRoleIds(List<Long> ids) {
        return permissionDao.getPermissionsByRoleIds(ids);
    }
}
