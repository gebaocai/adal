package me.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.mapper.SysDepartPermissionDao;
import me.baocai.adal.web.playload.DepartPermission;
import me.baocai.adal.web.service.SysDepartPermissionService;
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
public class SysDepartPermissionServiceImpl extends ServiceImpl<SysDepartPermissionDao, SysDepartPermission> implements SysDepartPermissionService {

    @Override
    @Transactional
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

        boolean result = saveBatch(sysDepartPermissions);
        return result;
    }
}
