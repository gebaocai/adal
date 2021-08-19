package me.baocai.adal.web.service;

import me.baocai.adal.web.entity.SysUserDepart;
import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.entity.SysUserRole;

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
    List<SysUserDepart> listByUserId(String userId);
    boolean saveBatch(List<String> departIds, String userId);
}
