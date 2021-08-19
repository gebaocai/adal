package me.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.entity.SysUserRole;
import me.baocai.adal.web.mapper.SysDepartPermissionDao;
import me.baocai.adal.web.playload.DepartPermission;
import me.baocai.adal.web.service.SysDepartPermissionService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
@CacheConfig(cacheNames = {"departPermissionCache"})
public class SysDepartPermissionServiceImpl extends ServiceImpl<SysDepartPermissionDao, SysDepartPermission> implements SysDepartPermissionService {

    @Override
    @Transactional
    @CacheEvict()
    public boolean saveDepartPermission(DepartPermission departPermission, String userId) {
        String departId = departPermission.getDepartId();
        String permissionIds = departPermission.getPermissionIds();
        String[] permissionIdAry = StrUtil.split(permissionIds, ",");
        List<SysDepartPermission> sysDepartPermissions = Arrays.stream(permissionIdAry).map(permissionId->{
            SysDepartPermission sysDepartPermission = SysDepartPermission.builder().
                    departId(departId).
                    permissionId(permissionId).
                    build();
            return sysDepartPermission;
        }).collect(Collectors.toList());

        List<SysDepartPermission> lastPermissions = list(lambdaQuery().eq(SysDepartPermission::getDepartId, departId).getWrapper());
        List<SysDepartPermission> addedPermissions = sysDepartPermissions.stream().filter(SysDepartPermission-> {
            return !lastPermissions.contains(SysDepartPermission);
        }).collect(Collectors.toList());

        List<String> removedPermissionIds = lastPermissions.stream().filter(SysDepartPermission-> {
            return !sysDepartPermissions.contains(SysDepartPermission);
        }).map(SysDepartPermission->SysDepartPermission.getId()).collect(Collectors.toList());

        boolean removeRes = removeByIds(removedPermissionIds);
        boolean saveRes = saveBatch(addedPermissions);
        return removeRes&&saveRes;
    }

    @Override
    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysDepartPermission> list(Long departId) {
        return list(lambdaQuery().eq(SysDepartPermission::getDepartId, departId).getWrapper());
    }
}
