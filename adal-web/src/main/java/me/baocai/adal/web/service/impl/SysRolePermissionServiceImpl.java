package me.baocai.adal.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.entity.SysRolePermission;
import me.baocai.adal.web.mapper.SysRolePermissionDao;
import me.baocai.adal.web.service.SysRolePermissionService;
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
    @Override
    public boolean saveRolePermission(String roleId, String permissionIds) {
        List idList = Arrays.asList(permissionIds.split(","));
        List<SysRolePermission> opsList = listByIds(idList);
        List<SysRolePermission> existList = list(lambdaQuery().eq(SysRolePermission::getRoleId, roleId));
        List<SysRolePermission> added = opsList.stream().filter(sysRolePermission->
                !existList.contains(sysRolePermission)).collect(Collectors.toList());
        List<String> removed = existList.stream().filter(sysRolePermission->
                !opsList.contains(sysRolePermission)).
                map(sysRolePermission->sysRolePermission.getId()).
                collect(Collectors.toList());

        boolean removeRes = removeByIds(removed);
        boolean saveRes = saveBatch(added);
        return removeRes&&saveRes;
    }

    @Override
    public List<SysRolePermission> list(String roleId) {
        return null;
    }
}
