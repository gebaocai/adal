package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysUserDepart;
import ga.baocai.adal.web.mapper.SysUserDepartDao;
import ga.baocai.adal.web.service.SysUserDepartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
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
public class SysUserDepartServiceImpl extends ServiceImpl<SysUserDepartDao, SysUserDepart> implements SysUserDepartService {

    @Override
    public List<String> listByUserId(String userId) {
        List<SysUserDepart> userDepartList = list(lambdaQuery().eq(SysUserDepart::getUserId, userId).getWrapper());
        List<String> departIds = userDepartList.stream().map(x->x.getDepartId()).collect(Collectors.toList());
        return departIds;
    }

    @Override
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
