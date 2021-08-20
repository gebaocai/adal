package me.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.entity.SysUserRole;
import me.baocai.adal.web.mapper.SysUserRoleDao;
import me.baocai.adal.web.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2021-08-19
 */
@Service
@CacheConfig(cacheNames = {"userRoleCache"})
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Override
    @Cacheable(key ="'listByUserId'+#userId")
    public List<SysUserRole> listByUserId(String userId) {
        return list(lambdaQuery().eq(SysUserRole::getUserId, userId).getWrapper());
    }

    @Override
    @CacheEvict(key ="'listByUserId'+#userId")
    public boolean saveBatch(List<String> roleIds, String userId) {
        List<SysUserRole> roles = roleIds.stream().map(roleId->{
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(roleId);
            sysUserRole.setUserId(userId);
            return sysUserRole;
        }).collect(Collectors.toList());

        List<SysUserRole> lastRoles = list(lambdaQuery().eq(SysUserRole::getUserId, userId).getWrapper());
        List<SysUserRole> addedRoles = roles.stream().filter(SysUserRole-> {
            return !lastRoles.contains(SysUserRole);
        }).collect(Collectors.toList());

        List<String> removedRoles = lastRoles.stream().filter(SysUserRole-> {
            return !roles.contains(SysUserRole);
        }).map(SysDepartPermission->SysDepartPermission.getId()).collect(Collectors.toList());

        boolean removeRes = CollUtil.isEmpty(removedRoles)?true:removeByIds(removedRoles);
        boolean saveRes = CollUtil.isEmpty(addedRoles)?true:saveBatch(addedRoles);
        return removeRes&&saveRes;
    }
}
