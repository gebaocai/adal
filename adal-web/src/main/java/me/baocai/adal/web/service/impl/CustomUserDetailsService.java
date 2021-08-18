package me.baocai.adal.web.service.impl;

import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysUser;
import me.baocai.adal.web.service.SysPermissionService;
import me.baocai.adal.web.service.SysRoleService;
import me.baocai.adal.web.service.SysUserService;
import me.baocai.adal.web.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByUsernameOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone)
                .orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));
        List<SysRole> roles = sysRoleService.getRolesByUserId(user.getId());
        List<SysPermission> permissions = roles.stream().map(sysRole -> {
            String roleId = sysRole.getId();
            return permissionService.getPermissionsByRoleId(roleId);
        }).flatMap(Collection::stream).collect(Collectors.toSet()).stream().collect(Collectors.toList());

        return UserPrincipal.create(user, roles, permissions);
    }
}
