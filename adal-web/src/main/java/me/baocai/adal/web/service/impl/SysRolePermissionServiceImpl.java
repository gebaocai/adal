package me.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.common.Consts;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.entity.SysRolePermission;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.mapper.SysRolePermissionDao;
import me.baocai.adal.web.service.SysPermissionService;
import me.baocai.adal.web.service.SysRolePermissionService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-24
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionDao, SysRolePermission> implements SysRolePermissionService {
    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private Enforcer enforcer;

    @Override
    public boolean saveRolePermission(String roleId, String permissionIds) {
//        List<String> idList = Arrays.asList(permissionIds.split(","));
//        //check exist
////        sysPermissionService.listByIds(idList);
//        idList.stream().forEach(id->{
//            sysPermissionService.getById(id);
//        });
//
//        List<SysRolePermission> opsList = idList.stream().map(id->{
//            return SysRolePermission.builder().roleId(roleId).permissionId(id).build();
//        }).collect(Collectors.toList());
//
//        List<SysRolePermission> existList = list(Wrappers.<SysRolePermission>lambdaQuery().eq(SysRolePermission::getRoleId, roleId));
//        List<SysRolePermission> added = opsList.stream().filter(sysRolePermission->
//                !existList.contains(sysRolePermission)).collect(Collectors.toList());
//        List<String> removed = existList.stream().filter(sysRolePermission->
//                !opsList.contains(sysRolePermission)).
//                map(sysRolePermission->sysRolePermission.getId()).
//                collect(Collectors.toList());
//
//        boolean removeRes = CollUtil.isEmpty(removed)?true:removeByIds(removed);
//        boolean saveRes = CollUtil.isEmpty(added)?true:saveBatch(added);
//        return removeRes&&saveRes;

        List<List<String>> permissions = enforcer.getPermissionsForUser(Consts.CASBIN_ROLE_KEY_PREFIX+roleId);
        List<String> lastPermissions = permissions.stream().map(x->x.get(1)).collect(Collectors.toList());

        List<String> permissionIdReq = Arrays.asList(permissionIds.split(","));

        List<String> addedPermissions = permissionIdReq.stream().filter(permissionId->{
            return !lastPermissions.contains(permissionId);
        }).collect(Collectors.toList());
        List<String> removedPermissions = lastPermissions.stream().filter(permissionId-> {
            return !permissionIdReq.contains(permissionId);
        }).collect(Collectors.toList());

        removedPermissions.forEach(permission -> {
            enforcer.deletePermissionForUser(Consts.CASBIN_ROLE_KEY_PREFIX+roleId, permission);
        });
        addedPermissions.forEach(permission -> {
            enforcer.addPermissionForUser(Consts.CASBIN_ROLE_KEY_PREFIX+roleId, permission);
        });
        return true;
    }

    @Override
    public List<String> list(String roleId) {
//        return list(lambdaQuery().eq(SysRolePermission::getRoleId, roleId).getWrapper());
        List<List<String>> permissions = enforcer.getPermissionsForUser(Consts.CASBIN_ROLE_KEY_PREFIX+roleId);
        List<String> lastPermissions = permissions.stream().map(x->x.get(1)).collect(Collectors.toList());
//        return list(lambdaQuery().eq(SysDepartPermission::getDepartId, departId).getWrapper());
        return lastPermissions;
    }
}
