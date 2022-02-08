package ga.baocai.adal.web.service;

import ga.baocai.adal.web.entity.SysRoleDataScope;
import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.playload.RoleDataScope;
import ga.baocai.adal.web.playload.UserDataScope;

import java.util.List;

/**
 * <p>
 * 系统角色数据范围表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
public interface SysRoleDataScopeService extends IService<SysRoleDataScope> {
    boolean saveRoleDataScope(RoleDataScope roleDataScope);

    List<String> list(String role);
}
