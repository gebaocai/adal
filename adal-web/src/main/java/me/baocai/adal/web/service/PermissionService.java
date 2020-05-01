package me.baocai.adal.web.service;

import me.baocai.adal.web.model.Permission;

import java.util.List;

public interface PermissionService {
    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<Permission> getPermissionsByRoleIds(List<Long> ids);

}
