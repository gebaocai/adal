package me.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.entity.SysUserRole;
import me.baocai.adal.web.mapper.SysUserDepartDao;
import me.baocai.adal.web.service.SysUserDepartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
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

    @Override
    @Cacheable(key ="'listByUserId'+#userId")
    public List<SysUserDepart> listByUserId(String userId) {
        return list(lambdaQuery().eq(SysUserDepart::getUserId, userId).getWrapper());
    }

    @Override
    @CacheEvict(key ="'listByUserId'+#userId")
    @Transient
    public boolean saveBatch(List<String> departIds, String userId) {
        List<SysUserDepart> departs = departIds.stream().map(departId->{
            SysUserDepart sysUserRole = new SysUserDepart();
            sysUserRole.setDepartId(departId);
            sysUserRole.setUserId(userId);
            return sysUserRole;
        }).collect(Collectors.toList());

        List<SysUserDepart> lastRoles = list(lambdaQuery().eq(SysUserDepart::getUserId, userId).getWrapper());
        List<SysUserDepart> addedRoles = departs.stream().filter(SysUserDepart-> {
            return !lastRoles.contains(SysUserDepart);
        }).collect(Collectors.toList());

        List<String> removedRoles = lastRoles.stream().filter(SysUserDepart-> {
            return !departs.contains(SysUserDepart);
        }).map(SysUserDepart->SysUserDepart.getId()).collect(Collectors.toList());

        boolean removeRes = CollUtil.isEmpty(removedRoles)?true:removeByIds(removedRoles);
        boolean saveRes = CollUtil.isEmpty(addedRoles)?true:saveBatch(addedRoles);
        return removeRes&&saveRes;
    }
}
