package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2021-08-19
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    List<String> listByUserId(String userId);
    IPage<SysUserRole> listByRoleId(Page page, String roleId);
    boolean saveBatch(List<String> roleIds, String userId);
}
