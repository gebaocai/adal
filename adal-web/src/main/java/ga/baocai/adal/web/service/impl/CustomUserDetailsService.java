package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysApi;
import ga.baocai.adal.web.entity.SysPermissionApi;
import ga.baocai.adal.web.entity.SysRole;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.service.*;
import ga.baocai.adal.web.vo.UserPrincipal;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserDepartService sysUserDepartService;

    @Autowired
    private SysDepartService sysDepartService;

    @Autowired
    private SysPermissionApiService sysPermissionApiService;

    @Autowired
    private SysApiService sysApiService;

    @Autowired
    private Enforcer enforcer;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByUsernameOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone)
                .orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));

//        List<String> sysDepartService.getDepartsByUserId(user.getId());
        Integer dataScopeType = Consts.DATA_SCOPE_ALL;
        List<String> dataScope = CollUtil.newArrayList();
        if (user.getUserType() != Consts.ADMIN) {
            dataScopeType = getDataScopeType(user.getId());
            dataScope = getUserDataScope(user.getId(), dataScopeType);
        }

        return UserPrincipal.create(user, getUserPermission(user), dataScopeType, dataScope);
    }

    private List<SysApi> getUserPermission(SysUser sysUser) {
        List<SysApi> apiList;
        if (sysUser.getUserType() == Consts.ADMIN) {
            return CollUtil.toList();
        } else {
            List<List<String>> permissions = enforcer.getImplicitPermissionsForUser(sysUser.getId());
            List<String> sysPermissionIds = permissions.stream().map(x -> {
                String permissionId = x.get(1);
                return permissionId;
            }).collect(Collectors.toList());
            List<String> sysPermissionIds1 = sysPermissionIds.stream().distinct().collect(Collectors.toList());
            List<SysPermissionApi> sysPermissionApiList = sysPermissionApiService.
                    list(sysPermissionApiService.lambdaQuery().in(
                    SysPermissionApi::getPermissionId,
                    sysPermissionIds1).getWrapper());
            List<String> sysPermissionApiIds = sysPermissionApiList.stream().map(x->x.getApiId()).distinct().collect(Collectors.toList());
            apiList = sysApiService.listByIds(sysPermissionApiIds);
        }
        return apiList;
    }

    private Integer getDataScopeType(String userId) {
        List<String> roleIds = sysUserRoleService.listByUserId(userId);
        List<SysRole> roleList = sysRoleService.listByIds(roleIds);
        //find max data scope
        Integer strongerDataScopeType = Consts.DATA_SCOPE_SELF;
        for (SysRole sysRole: roleList) {
            if (sysRole.getDataScopeType() <= strongerDataScopeType) {
                strongerDataScopeType = sysRole.getDataScopeType();
            }
        }
        return strongerDataScopeType;
    }

    private List<String> getUserDataScope(String userId, Integer strongerDataScopeType) {
        List<String> departIds = sysUserDepartService.listByUserId(userId);
        List<String> dataScope = sysDepartService.getDataScopeListByDataScopeType(strongerDataScopeType, departIds);

        List<List<String>> permissions = enforcer.getImplicitPermissionsForUser(userId);
        List<String> lastPermissions = permissions.stream().filter(
                x-> {String permissionType = x.get(1);
                    return Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE.equals(permissionType);}
        ).map(x->x.get(2)).collect(Collectors.toList());

        CollUtil.addAll(dataScope, lastPermissions);
        dataScope = dataScope.stream().distinct().collect(Collectors.toList());
        return dataScope;
    }
}
