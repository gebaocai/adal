package ga.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.entity.SysPermission;
import ga.baocai.adal.web.model.PermissionData;
import ga.baocai.adal.web.model.SysPermissionTree;
import ga.baocai.adal.web.playload.Api;
import ga.baocai.adal.web.playload.Permission;

import java.util.List;

public interface SysPermissionService extends IService<SysPermission> {
    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleIds(List<String> ids);

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleId(String roleId);

    /**
     * 根据部门列表查询权限列表
     *
     * @param ids 部门id列表
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByDepartIds(List<String> ids);

    /**
     * 根据角色列表查询权限列表
     *
     * @param departId 部门id
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByDepartId(String departId);

    List<SysPermissionTree> queryTreeList();

    /**
     * 根据用户id查找其权限
     *
     * @param userId
     * @return
     */
    PermissionData getUserPermission(String userId);

    SysPermission savePermission(Permission permission, String userId);

    boolean delete(String permissionId);

    boolean hasChildren(Permission permission);

    boolean deleteForce(Permission permission);

    SysPermission updatePermissionById(Permission permission, String userId);
}
