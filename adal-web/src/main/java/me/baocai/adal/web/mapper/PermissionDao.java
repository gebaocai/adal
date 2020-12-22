package me.baocai.adal.web.mapper;

import me.baocai.adal.web.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 权限 DAO
 * </p>
 */
@Mapper
@Component
public interface PermissionDao {

    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleIds(@Param("ids") List<Long> ids);

    /**
     * 根据角色列表查询权限列表
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    List<SysPermission> getPermissionsByRoleId(@Param("roleId") String roleId);

}
