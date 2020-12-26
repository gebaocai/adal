package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.model.SysDepartTreeModel;

import java.util.List;

/**
 * <p>
 * 组织机构表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
public interface SysDepartService extends IService<SysDepart> {

    List<SysDepartTreeModel> queryTreeList();

    boolean saveDepartData(SysDepart sysDepart, String userId);

    boolean delete(String departId);

    boolean updateDepartDataById(SysDepart sysDepart, String userId);

}
