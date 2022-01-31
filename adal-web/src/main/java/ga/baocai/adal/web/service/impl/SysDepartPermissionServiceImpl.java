package ga.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysDepartPermission;
import ga.baocai.adal.web.mapper.SysDepartPermissionDao;
import ga.baocai.adal.web.playload.DepartPermission;
import ga.baocai.adal.web.service.SysDepartPermissionService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门权限表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Service
//@CacheConfig(cacheNames = {"departPermissionCache"})
public class SysDepartPermissionServiceImpl extends ServiceImpl<SysDepartPermissionDao, SysDepartPermission> implements SysDepartPermissionService {
    @Autowired
    private Enforcer enforcer;

    @Override
    @Transactional
//    @CacheEvict()
    public boolean saveDepartPermission(DepartPermission departPermission, String userId) {
//        String departId = departPermission.getDepartId();
//        String permissionIds = departPermission.getPermissionIds();
//        String[] permissionIdAry = StrUtil.split(permissionIds, ",");
//        List<SysDepartPermission> sysDepartPermissions = Arrays.stream(permissionIdAry).map(permissionId->{
//            SysDepartPermission sysDepartPermission = SysDepartPermission.builder().
//                    departId(departId).
//                    permissionId(permissionId).
//                    build();
//            return sysDepartPermission;
//        }).collect(Collectors.toList());
//        List<SysDepartPermission> lastPermissions = list(lambdaQuery().eq(SysDepartPermission::getDepartId, departId).getWrapper());
//        List<SysDepartPermission> addedPermissions = sysDepartPermissions.stream().filter(SysDepartPermission-> {
//            return !lastPermissions.contains(SysDepartPermission);
//        }).collect(Collectors.toList());
//
//        List<String> removedPermissionIds = lastPermissions.stream().filter(SysDepartPermission-> {
//            return !sysDepartPermissions.contains(SysDepartPermission);
//        }).map(SysDepartPermission->SysDepartPermission.getId()).collect(Collectors.toList());
//
//        boolean removeRes = CollUtil.isEmpty(removedPermissionIds)?true:removeByIds(removedPermissionIds);
//        boolean saveRes = CollUtil.isEmpty(addedPermissions)?true:saveBatch(addedPermissions);
//        return removeRes&&saveRes;

        String departId = departPermission.getDepartId();
        List<List<String>> permissions = enforcer.getPermissionsForUser(Consts.CASBIN_DEPART_KEY_PREFIX+departId);
        List<String> lastPermissions = permissions.stream().map(x->x.get(1)).collect(Collectors.toList());
        String permissionIds = departPermission.getPermissionIds();
        String[] permissionIdAry = StrUtil.split(permissionIds, ",");
        List<String> permissionIdReq = Arrays.asList(permissionIdAry);

        List<String> addedPermissions = permissionIdReq.stream().filter(permissionId->{
            return !lastPermissions.contains(permissionId);
        }).collect(Collectors.toList());
        List<String> removedPermissions = lastPermissions.stream().filter(permissionId-> {
            return !permissionIdReq.contains(permissionId);
        }).collect(Collectors.toList());

        removedPermissions.forEach(permission -> {
            enforcer.deletePermissionForUser(Consts.CASBIN_DEPART_KEY_PREFIX+departId, permission);
        });
        addedPermissions.forEach(permission -> {
            enforcer.addPermissionForUser(Consts.CASBIN_DEPART_KEY_PREFIX+departId, permission);
        });

        return true;
    }

    @Override
//    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<String> list(String departId) {
        List<List<String>> permissions = enforcer.getPermissionsForUser(Consts.CASBIN_DEPART_KEY_PREFIX+departId);
        List<String> lastPermissions = permissions.stream().map(x->x.get(1)).collect(Collectors.toList());
//        return list(lambdaQuery().eq(SysDepartPermission::getDepartId, departId).getWrapper());
        return lastPermissions;
    }
}
