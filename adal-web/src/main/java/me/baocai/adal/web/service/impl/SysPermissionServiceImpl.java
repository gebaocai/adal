package me.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.mapper.SysPermissionDao;
import me.baocai.adal.web.model.SysDepartTreeModel;
import me.baocai.adal.web.model.SysPermissionTree;
import me.baocai.adal.web.playload.Permission;
import me.baocai.adal.web.service.SysPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Service
//@CacheConfig(cacheNames = {"permissionCache"})
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionDao, SysPermission> implements SysPermissionService {

    @Override
//    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> getPermissionsByRoleIds(List<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            return CollUtil.toList();
        }
        return baseMapper.getPermissionsByRoleIds(ids);
    }

    @Override
//    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysPermission> getPermissionsByRoleId(String roleId) {
        return baseMapper.getPermissionsByRoleId(roleId);
    }

    @Override
    public List<SysPermission> getPermissionsByDepartIds(List<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            return CollUtil.toList();
        }
        return baseMapper.getPermissionsByDepartIds(ids);
    }

    @Override
    public List<SysPermission> getPermissionsByDepartId(String departId) {
        return baseMapper.getPermissionsByDepartId(departId);
    }

    @Override
    public List<SysPermissionTree> queryTreeList() {
        List<SysPermission> permissionList = baseMapper.selectList(new QueryWrapper<SysPermission>().lambda().
                orderByAsc(SysPermission::getSortNo));
        return convert2TreeModel(permissionList);

    }

    @Override
    public List<SysPermission> getUserPermission(String userId) {
        return null;
    }

    @Override
    public SysPermission savePermission(Permission permission, String userId) {
        if (permission == null || StrUtil.isEmpty(permission.getName())) {
            return null;
        }
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        SysPermission sysPermission = SysPermission.builder().build();
        BeanUtils.copyProperties(permission, sysPermission);
        if (StrUtil.isEmpty(sysPermission.getParentId())) {
            sysPermission.setParentId("");
        }
        if (save(sysPermission)) {
            return sysPermission;
        }
        return null;
    }

    @Override
    public SysPermission updatePermissionById(Permission permission, String userId) {
        if (permission == null || StrUtil.isEmpty(permission.getName())) {
            return null;
        }
        if (StrUtil.isEmpty(userId)) {
            return null;
        }
        SysPermission sysPermission = SysPermission.builder().build();
        BeanUtils.copyProperties(permission, sysPermission);
        sysPermission.setUpdateBy(userId);
        if (updateById(sysPermission)) {
            return sysPermission;
        }
        return null;
    }

    @Override
    public boolean delete(String permissionId) {
        return removeById(permissionId);
    }

    private List<SysPermissionTree> convert2TreeModel(List<SysPermission> list) {
        List<SysPermissionTree> rootTreeModel = list.stream()
                .filter(X -> StrUtil.isEmpty(X.getParentId()))
                .map(X -> new SysPermissionTree(X))
                .collect(Collectors.toList());
        findChildren(rootTreeModel, list);
        return rootTreeModel;
    }

    private void findChildren(List<SysPermissionTree> rootTreeModel, List<SysPermission> list) {
        rootTreeModel.stream().forEach(X -> {
            List<SysPermissionTree> childTreeModel = list.stream().filter(Y -> X.getId().equals(Y.getParentId()))
                    .map(Y -> new SysPermissionTree(Y))
                    .collect(Collectors.toList());
            if (!CollUtil.isEmpty(childTreeModel)) {
                X.setChildren(childTreeModel);
            }
            findChildren(childTreeModel, list);
        });
    }
}
