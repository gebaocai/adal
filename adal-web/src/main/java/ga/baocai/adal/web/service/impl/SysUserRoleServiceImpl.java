package ga.baocai.adal.web.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysUserRole;
import ga.baocai.adal.web.mapper.SysUserRoleDao;
import ga.baocai.adal.web.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//@CacheConfig(cacheNames = {"userRoleCache"})
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {
    @Autowired
    private Enforcer enforcer;

    @Override
//    @Cacheable(key ="'listByUserId'+#userId")
    public List<String> listByUserId(String userId) {
        List<String> casRoleIds = enforcer.getRolesForUser(userId);
        List<String> roleIds = casRoleIds.stream().filter(x->x.startsWith(Consts.CASBIN_ROLE_KEY_PREFIX)).
                map(x->x.replace(Consts.CASBIN_ROLE_KEY_PREFIX, "")).
                collect(Collectors.toList());
        return roleIds;
    }

    @Override
//    @Cacheable(key ="'listByUserId'+#userId")
    public IPage<SysUserRole> listByRoleId(Page page, String roleId) {
        List<String> userIds = enforcer.getUsersForRole(roleId);
        return page(page, lambdaQuery().eq(SysUserRole::getRoleId, roleId).getWrapper());


    }

    @Override
//    @CacheEvict(key ="'listByUserId'+#userId")
    public boolean saveBatch(List<String> roleIds, String userId) {
//        List<SysUserRole> roles = roleIds.stream().map(roleId->{
//            SysUserRole sysUserRole = new SysUserRole();
//            sysUserRole.setRoleId(roleId);
//            sysUserRole.setUserId(userId);
//            return sysUserRole;
//        }).collect(Collectors.toList());
//
//        List<SysUserRole> lastRoles = list(lambdaQuery().eq(SysUserRole::getUserId, userId).getWrapper());
//        List<SysUserRole> addedRoles = roles.stream().filter(SysUserRole-> {
//            return !lastRoles.contains(SysUserRole);
//        }).collect(Collectors.toList());
//
//        List<String> removedRoles = lastRoles.stream().filter(SysUserRole-> {
//            return !roles.contains(SysUserRole);
//        }).map(SysDepartPermission->SysDepartPermission.getId()).collect(Collectors.toList());
//
//        boolean removeRes = CollUtil.isEmpty(removedRoles)?true:removeByIds(removedRoles);
//        boolean saveRes = CollUtil.isEmpty(addedRoles)?true:saveBatch(addedRoles);
//        return removeRes&&saveRes;
        List<String> casRoleIds = enforcer.getRolesForUser(userId);
        List<String> lastRoles = casRoleIds.stream().filter(x->x.startsWith(Consts.CASBIN_ROLE_KEY_PREFIX)).
                map(x->x.replace(Consts.CASBIN_ROLE_KEY_PREFIX, "")).
                collect(Collectors.toList());
        List<String> addedRoles = roleIds.stream().filter(roleId-> {
            return !lastRoles.contains(roleId);
        }).collect(Collectors.toList());

        List<String> removedRoles = lastRoles.stream().filter(role-> {
            return !roleIds.contains(role);
        }).collect(Collectors.toList());
        removedRoles.forEach(roleId->enforcer.deleteRoleForUser(userId, Consts.CASBIN_ROLE_KEY_PREFIX+roleId));
        addedRoles.forEach(roleId->enforcer.addRoleForUser(userId, Consts.CASBIN_ROLE_KEY_PREFIX+roleId));

        return true;
    }
}
