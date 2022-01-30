package me.baocai.adal.web.service;

import me.baocai.adal.web.entity.SysApi;
import me.baocai.adal.web.entity.SysPermissionApi;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关系表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-22
 */
public interface SysPermissionApiService extends IService<SysPermissionApi> {
    List<String> list(String permissionId);
    boolean saveBatch(List<String> apiIds, String permissionId);
}
