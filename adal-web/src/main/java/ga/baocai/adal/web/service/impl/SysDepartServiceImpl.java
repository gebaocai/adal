package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.playload.Depart;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.mapper.SysDepartDao;
import ga.baocai.adal.web.result.SysDepartTreeModel;
import ga.baocai.adal.web.rule.OrgCodeRule;
import ga.baocai.adal.web.service.SysDepartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 组织机构表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Service
public class SysDepartServiceImpl extends ServiceImpl<SysDepartDao, SysDepart> implements SysDepartService {

    @Autowired
    private OrgCodeRule orgCodeRule;

    @Override
    public List<SysDepartTreeModel> queryTreeList() {
        List<SysDepart> list = list(lambdaQuery().orderByAsc(SysDepart::getDepartOrder).getWrapper());
        List<SysDepartTreeModel> treeModelList = convert2TreeModel(list);
        return treeModelList;
    }

    @Override
    @Transactional
    public SysDepart saveDepartData(Depart depart, String userId) {
        if (depart == null || StrUtil.isEmpty(depart.getDepartName())) {
            return null;
        }
        if (depart == null) {
            return null;
        }
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        SysDepart sysDepart = SysDepart.builder().build();
        BeanUtils.copyProperties(depart, sysDepart);
        if (StrUtil.isEmpty(sysDepart.getParentId())) {
            sysDepart.setParentId("");
        }
        String[] codeArray = orgCodeRule.getOrgCode(sysDepart.getParentId());
        sysDepart.setOrgCode(codeArray[0]);
        sysDepart.setOrgType(codeArray[1]);
        sysDepart.setCreateBy(userId);
        sysDepart.setCreateTime(DateTime.now());
        if (save(sysDepart)) {
            return sysDepart;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(String departId) {
        return removeById(departId);
    }

    @Override
    @Transactional
    public SysDepart updateDepartDataById(Depart depart, String userId) {
        if (depart == null || StrUtil.isEmpty(depart.getDepartName())) {
            return null;
        }
        if (depart == null) {
            return null;
        }
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        SysDepart sysDepart = SysDepart.builder().build();
        BeanUtils.copyProperties(depart, sysDepart);
        sysDepart.setUpdateBy(userId);
        sysDepart.setUpdateTime(DateTime.now());
        if (updateById(sysDepart)) {
            return sysDepart;
        }
        return null;
    }

    private List<SysDepartTreeModel> convert2TreeModel(List<SysDepart> list) {
        List<SysDepartTreeModel> rootTreeModel = list.stream()
                .filter(X -> StrUtil.isEmpty(X.getParentId()))
                .map(X -> new SysDepartTreeModel(X))
                .collect(Collectors.toList());
        findChildren(rootTreeModel, list);
        return rootTreeModel;
    }

    private void findChildren(List<SysDepartTreeModel> rootTreeModel, List<SysDepart> list) {
        rootTreeModel.stream().forEach(X -> {
            List<SysDepartTreeModel> childTreeModel = list.stream().filter(Y -> Y.getParentId().equals(X.getId()))
                    .map(Y -> new SysDepartTreeModel(Y))
                    .collect(Collectors.toList());
            X.setChildren(childTreeModel);
            findChildren(childTreeModel, list);
        });
    }

    @Override
    public List<SysDepart> getDepartsByUserId(String userId) {
        return null;
    }

    @Override
    public List<String> getDataScopeListByDataScopeType(Integer dataScopeType, List<String> orgId) {

        List<String> resultList = CollectionUtil.newArrayList();

        if (CollectionUtil.isEmpty(orgId)) {
            return CollectionUtil.newArrayList();
        }

        // 如果是范围类型是全部数据，则获取当前系统所有的组织架构id
        if (Consts.DATA_SCOPE_ALL.equals(dataScopeType)) {
            resultList = this.getOrgIdAll();
        }
        // 如果范围类型是本部门及以下部门，则查询本节点和子节点集合，包含本节点
        else if (Consts.DATA_SCOPE_DEPART_AND_SUB.equals(dataScopeType)) {
            resultList = this.getChildIdListWithSelfById(orgId);
        }
        // 如果数据范围是本部门，不含子节点，则直接返回本部门
        else if (Consts.DATA_SCOPE_DEPART.equals(dataScopeType)) {
            resultList.addAll(orgId);
        }

        return resultList;
    }

    private List<String> getOrgIdAll() {
        List<String> resultList = CollectionUtil.newArrayList();

        LambdaQueryWrapper<SysDepart> queryWrapper = new LambdaQueryWrapper<>();

        this.list(queryWrapper).forEach(sysOrg -> resultList.add(sysOrg.getId()));
        return resultList;
    }

    private List<String> getChildIdListWithSelfById(List<String> orgIds) {
        List<String> resultList = CollectionUtil.newArrayList();
        List<SysDepart> sysDepartList = list();
        findChild(resultList, orgIds, sysDepartList);
        resultList.addAll(orgIds);
        return resultList;
    }

    private void findChild(List<String> resultList, List<String> orgIds, List<SysDepart> sysDepartList) {
        if (CollectionUtil.isEmpty(orgIds)) {
            return;
        }
        sysDepartList.stream().forEach(X->{
            List<String> subOrgIds = orgIds.stream().filter(Y->Y.equals(X.getParentId())).collect(Collectors.toList());
            findChild(resultList, subOrgIds, sysDepartList);
        });
    }
}
