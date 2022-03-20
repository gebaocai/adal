package ga.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.playload.Depart;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.result.SysDepartTreeModel;

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

    SysDepart saveDepartData(Depart depart, String userId);

    boolean delete(String departId);

    SysDepart updateDepartDataById(Depart depart, String userId);

    /**
     * 根据用户id 查询部门列表
     *
     * @param userId 用户id
     * @return 部门列表
     */
    List<SysDepart> getDepartsByUserId(String userId);

    List<String> getDataScopeListByDataScopeType(Integer dataScopeType, List<String> orgId);

    void export();

}
