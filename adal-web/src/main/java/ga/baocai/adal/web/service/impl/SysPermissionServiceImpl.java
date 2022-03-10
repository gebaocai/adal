package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysPermission;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.mapper.SysPermissionDao;
import ga.baocai.adal.web.result.PermissionData;
import ga.baocai.adal.web.result.SysPermissionTree;
import ga.baocai.adal.web.playload.Permission;
import ga.baocai.adal.web.service.SysPermissionService;
import ga.baocai.adal.web.service.SysUserService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private Enforcer enforcer;
    @Autowired
    private SysUserService sysUserService;

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
    public PermissionData getUserPermission(String userId) {
        SysUser sysUser = sysUserService.getById(userId);
        List<SysPermission> permissionList;
        if (sysUser.getUsername().equals("admin")) {
            permissionList = baseMapper.selectList(new QueryWrapper<SysPermission>().lambda().
                    orderByAsc(SysPermission::getSortNo));
        } else {
            List<List<String>> permissions = enforcer.getImplicitPermissionsForUser(userId);
            List<String> sysPermissionIds = permissions.stream().map(x -> {
                String permissionId = x.get(1);
                return permissionId;
            }).collect(Collectors.toList());
            List<String> sysPermissionIds1 = sysPermissionIds.stream().distinct().collect(Collectors.toList());
            permissionList = listByIds(sysPermissionIds1);
        }
        PermissionData permissionData = convert2MenuData(permissionList);
        return permissionData;
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

    @Override
    public boolean hasChildren(Permission permission) {
        SysPermission sysPermission = getOne(lambdaQuery().eq(SysPermission::getParentId, permission.getId()).getWrapper(), false);
        return sysPermission != null;
    }

    @Transactional
    @Override
    public boolean deleteForce(Permission permission) {
        List<SysPermission> sysApiList = list(lambdaQuery().eq(SysPermission::getParentId, permission.getId()).getWrapper());
        List<String> ids = CollUtil.toList(permission.getId());
        findChildren2(sysApiList, ids);
        return removeByIds(ids);
    }

    private void findChildren2(List<SysPermission> sysApiList, List<String> ids) {
        sysApiList.stream().forEach(X -> {
            ids.add(X.getId());
            List<SysPermission> sysApiList1 = list(lambdaQuery().eq(SysPermission::getParentId, X.getId()).getWrapper());
            findChildren2(sysApiList1, ids);
        });
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

    private PermissionData convert2MenuData(List<SysPermission> list) {
        List<PermissionData.MenuData> menuData = list.stream()
                .filter(X -> X.getMenuType() == Consts.MENU)
                .map(X -> new PermissionData.MenuData(X))
                .collect(Collectors.toList());
        findChildren1(menuData, list);

        List<String> perms = list.stream()
                .filter(X -> X.getMenuType() == Consts.BUTTON)
                .map(X -> X.getPerms())
                .collect(Collectors.toList());

        PermissionData permissionData = new PermissionData();
        permissionData.setMenuData(menuData);
        permissionData.setPerms(perms);
        return permissionData;
    }

    private void findChildren1(List<PermissionData.MenuData> permissionData, List<SysPermission> list) {
        permissionData.stream().forEach(X -> {
            List<PermissionData.MenuData> childTreeModel = list.stream().filter(Y -> X.getId().equals(Y.getParentId()))
                    .map(Y -> new PermissionData.MenuData(Y))
                    .collect(Collectors.toList());
            if (!CollUtil.isEmpty(childTreeModel)) {
                X.setRoutes(childTreeModel);
            }
            findChildren1(childTreeModel, list);
        });
    }
}
