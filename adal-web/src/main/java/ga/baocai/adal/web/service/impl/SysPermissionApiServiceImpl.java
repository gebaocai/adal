package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import ga.baocai.adal.web.entity.SysPermissionApi;
import ga.baocai.adal.web.mapper.SysPermissionApiDao;
import ga.baocai.adal.web.service.SysApiService;
import ga.baocai.adal.web.service.SysPermissionApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-22
 */
@Service
public class SysPermissionApiServiceImpl extends ServiceImpl<SysPermissionApiDao, SysPermissionApi> implements SysPermissionApiService {
    @Autowired
    private SysApiService sysApiService;

    @Override
    public List<String> list(String permissionId) {
        List<SysPermissionApi> lastRoles = list(lambdaQuery().eq(SysPermissionApi::getPermissionId, permissionId).getWrapper());
        List<String> apiIds = lastRoles.stream().map(x->x.getApiId()).collect(Collectors.toList());
//        List<SysApi> sysApis = sysApiService.listByIds(apiIds);
        return apiIds;
    }

    @Override
    public boolean saveBatch(List<String> apiIds, String permissionId) {
        List<SysPermissionApi> roles = apiIds.stream().map(apiId->{
            SysPermissionApi sysPermissionApi = new SysPermissionApi();
            sysPermissionApi.setApiId(apiId);
            sysPermissionApi.setPermissionId(permissionId);
            return sysPermissionApi;
        }).collect(Collectors.toList());

        List<SysPermissionApi> lastRoles = list(lambdaQuery().eq(SysPermissionApi::getPermissionId, permissionId).getWrapper());
        List<SysPermissionApi> addedRoles = roles.stream().filter(sysPermissionApi-> {
            return !lastRoles.contains(sysPermissionApi);
        }).collect(Collectors.toList());

        List<String> removedRoles = lastRoles.stream().filter(sysPermissionApi-> {
            return !roles.contains(sysPermissionApi);
        }).map(SysDepartPermission->SysDepartPermission.getId()).collect(Collectors.toList());

        boolean removeRes = CollUtil.isEmpty(removedRoles)?true:removeByIds(removedRoles);
        boolean saveRes = CollUtil.isEmpty(addedRoles)?true:saveBatch(addedRoles);
        return removeRes&&saveRes;
    }
}
