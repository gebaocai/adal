package ga.baocai.adal.web.service;

import ga.baocai.adal.web.entity.SysUserDataScope;
import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.playload.DepartPermission;
import ga.baocai.adal.web.playload.UserDataScope;

import java.util.List;

/**
 * <p>
 * 系统用户数据范围表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
public interface SysUserDataScopeService extends IService<SysUserDataScope> {
    boolean saveUserDataScope(UserDataScope userDataScope);

    List<String> list(String userId);
}
