package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.entity.SysRolePermission;
import me.baocai.adal.web.playload.DepartPermission;

import java.util.List;

/**
 * <p>
 * 角色权限表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-24
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {

    /**
     * 保存角色和权限的关联
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(String roleId, String permissionIds);

    List<String> list(String roleId);
}
