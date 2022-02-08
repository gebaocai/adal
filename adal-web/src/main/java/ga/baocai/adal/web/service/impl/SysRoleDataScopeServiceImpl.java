package ga.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysRoleDataScope;
import ga.baocai.adal.web.mapper.SysRoleDataScopeDao;
import ga.baocai.adal.web.playload.RoleDataScope;
import ga.baocai.adal.web.playload.UserDataScope;
import ga.baocai.adal.web.service.SysRoleDataScopeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统角色数据范围表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
@Service
public class SysRoleDataScopeServiceImpl extends ServiceImpl<SysRoleDataScopeDao, SysRoleDataScope> implements SysRoleDataScopeService {
    @Autowired
    private Enforcer enforcer;

    @Override
    public boolean saveRoleDataScope(RoleDataScope roleDataScope) {
        String roleId = roleDataScope.getRoleId();
        List<List<String>> permissions = enforcer.getPermissionsForUser(Consts.CASBIN_ROLE_KEY_PREFIX+roleId);
        List<String> lastPermissions = permissions.stream().filter(
                x-> {String permissionType = x.get(1);
                    return Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE.equals(permissionType);}
        ).map(x->x.get(2)).collect(Collectors.toList());
        String departIds = roleDataScope.getDepartIds();
        String[] departIdAry = StrUtil.split(departIds, ",");
        List<String> departIdReq = Arrays.asList(departIdAry);

        List<String> addedPermissions = departIdReq.stream().filter(permissionId->{
            return !lastPermissions.contains(permissionId);
        }).collect(Collectors.toList());
        List<String> removedPermissions = lastPermissions.stream().filter(permissionId-> {
            return !departIdReq.contains(permissionId);
        }).collect(Collectors.toList());

        removedPermissions.forEach(permission -> {
            enforcer.deletePermissionForUser(roleId, Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE, permission);
        });
        addedPermissions.forEach(permission -> {
            enforcer.addPermissionForUser(roleId, Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE, permission);
        });

        return true;
    }

    @Override
    public List<String> list(String roleId) {
        List<List<String>> permissions = enforcer.getPermissionsForUser(Consts.CASBIN_ROLE_KEY_PREFIX+roleId);
        List<String> lastPermissions = permissions.stream().filter(
                x-> {String permissionType = x.get(1);
                    return Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE.equals(permissionType);}
        ).map(x->x.get(2)).collect(Collectors.toList());
        return lastPermissions;
    }
}
