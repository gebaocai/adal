package me.baocai.adal.web.service;

import me.baocai.adal.web.model.SysPermission;

import java.util.List;

public interface SysPermissionService {
    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleIds(List<Long> ids);

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleId(String roleId);

}
