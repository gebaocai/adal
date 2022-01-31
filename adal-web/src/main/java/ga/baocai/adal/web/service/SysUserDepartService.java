package ga.baocai.adal.web.service;

import ga.baocai.adal.web.entity.SysUserDepart;
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
public interface SysUserDepartService extends IService<SysUserDepart> {
    List<String> listByUserId(String userId);
    boolean saveBatch(List<String> departIds, String userId);
}
