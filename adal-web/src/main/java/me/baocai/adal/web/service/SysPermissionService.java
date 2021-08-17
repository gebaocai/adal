package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.entity.SysPermission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {
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

    @Override
    List<SysPermission> list();

    /**
     * 根据用户id查找其权限
     *
     * @param userId
     * @return
     */
    List<SysPermission> getUserPermission(String userId);
}