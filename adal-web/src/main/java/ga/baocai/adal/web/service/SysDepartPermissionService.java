package ga.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.entity.SysDepartPermission;
import ga.baocai.adal.web.playload.DepartPermission;

import java.util.List;

/**
 * <p>
 * 部门权限表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
public interface SysDepartPermissionService extends IService<SysDepartPermission> {
    boolean saveDepartPermission(DepartPermission departPermission, String userId);

    List<String> list(String departId);

}
