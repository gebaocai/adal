package me.baocai.adal.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.baocai.adal.web.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
public interface SysPermissionDao extends BaseMapper<SysPermission> {

    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleIds(@Param("ids") List<String> ids);

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByDepartIds(@Param("ids") List<String> ids);

    /**
     * 根据部门列表查询权限列表
     *
     * @param departId 部门id
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByDepartId(@Param("departId") String departId);

}
