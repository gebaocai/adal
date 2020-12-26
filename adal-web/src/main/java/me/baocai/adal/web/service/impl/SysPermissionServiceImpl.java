package me.baocai.adal.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.mapper.SysPermissionDao;
import me.baocai.adal.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Service
@CacheConfig(cacheNames = {"permissionCache"})
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

    @Autowired
    private SysPermissionDao sysPermissionDao;

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> getPermissionsByRoleIds(List<Long> ids) {
        return sysPermissionDao.getPermissionsByRoleIds(ids);
    }

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> getPermissionsByRoleId(String roleId) {
        return sysPermissionDao.getPermissionsByRoleId(roleId);
    }

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> list() {
        List<SysPermission> permissionList = sysPermissionDao.selectList(new QueryWrapper<SysPermission>().lambda().
                orderByAsc(SysPermission::getSortNo));
        return permissionList;
    }

    @Override
    public List<SysPermission> getUserPermission(String userId) {
        return null;
    }
}
