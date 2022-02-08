package ga.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysUserDataScope;
import ga.baocai.adal.web.mapper.SysUserDataScopeDao;
import ga.baocai.adal.web.playload.DepartPermission;
import ga.baocai.adal.web.playload.UserDataScope;
import ga.baocai.adal.web.service.SysUserDataScopeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户数据范围表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
@Service
public class SysUserDataScopeServiceImpl extends ServiceImpl<SysUserDataScopeDao, SysUserDataScope> implements SysUserDataScopeService {
    @Autowired
    private Enforcer enforcer;

    @Override
    public boolean saveUserDataScope(UserDataScope userDataScope) {
        String userId = userDataScope.getUserId();
        List<List<String>> permissions = enforcer.getPermissionsForUser(userId);
        List<String> lastPermissions = permissions.stream().filter(
                x-> {String permissionType = x.get(1);
                return Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE.equals(permissionType);}
        ).map(x->x.get(2)).collect(Collectors.toList());
        String departIds = userDataScope.getDepartIds();
        String[] departIdAry = StrUtil.split(departIds, ",");
        List<String> departIdReq = Arrays.asList(departIdAry);

        List<String> addedPermissions = departIdReq.stream().filter(permissionId->{
            return !lastPermissions.contains(permissionId);
        }).collect(Collectors.toList());
        List<String> removedPermissions = lastPermissions.stream().filter(permissionId-> {
            return !departIdReq.contains(permissionId);
        }).collect(Collectors.toList());

        removedPermissions.forEach(permission -> {
            enforcer.deletePermissionForUser(userId, Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE, permission);
        });
        addedPermissions.forEach(permission -> {
            enforcer.addPermissionForUser(userId, Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE, permission);
        });

        return true;
    }

    @Override
    public List<String> list(String userId) {
        List<List<String>> permissions = enforcer.getPermissionsForUser(userId);
        List<String> lastPermissions = permissions.stream().filter(
                x-> {String permissionType = x.get(1);
                    return Consts.CASBIN_PERMISSON_TYPE_DATA_SCOPE.equals(permissionType);}
        ).map(x->x.get(2)).collect(Collectors.toList());
        return lastPermissions;
    }
}
