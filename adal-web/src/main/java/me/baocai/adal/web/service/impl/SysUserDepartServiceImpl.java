package me.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import me.baocai.adal.web.common.Consts;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.entity.SysUserRole;
import me.baocai.adal.web.mapper.SysUserDepartDao;
import me.baocai.adal.web.service.SysDepartService;
import me.baocai.adal.web.service.SysUserDepartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2021-08-19
 */
@Service
@CacheConfig(cacheNames = {"userDepartCache"})
public class SysUserDepartServiceImpl extends ServiceImpl<SysUserDepartDao, SysUserDepart> implements SysUserDepartService {

    @Autowired
    private Enforcer enforcer;

    @Override
//    @Cacheable(key ="'listByUserId'+#userId")
    public List<String> listByUserId(String userId) {
        List<String> casRoleIds = enforcer.getRolesForUser(userId);
        List<String> roleIds = casRoleIds.stream().filter(x->x.startsWith(Consts.CASBIN_DEPART_KEY_PREFIX)).
                map(x->x.replace(Consts.CASBIN_DEPART_KEY_PREFIX, "")).
                collect(Collectors.toList());
        return roleIds;
    }

    @Override
//    @CacheEvict(key ="'listByUserId'+#userId")
    @Transient
    public boolean saveBatch(List<String> departIds, String userId) {
//        List<SysUserDepart> departs = departIds.stream().map(departId->{
//            SysUserDepart sysUserRole = new SysUserDepart();
//            sysUserRole.setDepartId(departId);
//            sysUserRole.setUserId(userId);
//            return sysUserRole;
//        }).collect(Collectors.toList());
//
//        List<SysUserDepart> lastRoles = list(lambdaQuery().eq(SysUserDepart::getUserId, userId).getWrapper());
//        List<SysUserDepart> addedRoles = departs.stream().filter(SysUserDepart-> {
//            return !lastRoles.contains(SysUserDepart);
//        }).collect(Collectors.toList());
//
//        List<String> removedRoles = lastRoles.stream().filter(SysUserDepart-> {
//            return !departs.contains(SysUserDepart);
//        }).map(SysUserDepart->SysUserDepart.getId()).collect(Collectors.toList());
//
//        boolean removeRes = CollUtil.isEmpty(removedRoles)?true:removeByIds(removedRoles);
//        boolean saveRes = CollUtil.isEmpty(addedRoles)?true:saveBatch(addedRoles);
//        return removeRes&&saveRes;
        List<String> casRoleIds = enforcer.getRolesForUser(userId);
        List<String> lastRoles = casRoleIds.stream().filter(x->x.startsWith(Consts.CASBIN_ROLE_KEY_PREFIX)).
                map(x->x.replace(Consts.CASBIN_DEPART_KEY_PREFIX, "")).
                collect(Collectors.toList());
        List<String> addedRoles = departIds.stream().filter(roleId-> {
            return !lastRoles.contains(roleId);
        }).collect(Collectors.toList());

        List<String> removedRoles = lastRoles.stream().filter(role-> {
            return !departIds.contains(role);
        }).collect(Collectors.toList());
        removedRoles.forEach(roleId->enforcer.deleteRoleForUser(userId, Consts.CASBIN_DEPART_KEY_PREFIX+roleId));
        addedRoles.forEach(roleId->enforcer.addRoleForUser(userId, Consts.CASBIN_DEPART_KEY_PREFIX+roleId));
        return true;
    }
}
