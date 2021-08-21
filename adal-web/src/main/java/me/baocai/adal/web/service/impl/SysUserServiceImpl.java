package me.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysUser;
import me.baocai.adal.web.mapper.SysUserDao;
import me.baocai.adal.web.playload.User;
import me.baocai.adal.web.service.SysUserDepartService;
import me.baocai.adal.web.service.SysUserRoleService;
import me.baocai.adal.web.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"userCache"})
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserDepartService sysUserDepartService;

    @Override
    @Cacheable(key ="'findByUsernameOrPhone_'+#username+'_'+#phone")
    public Optional<SysUser> findByUsernameOrPhone(String username, String phone) {
        return baseMapper.findByUsernameOrPhone(username, phone);
    }

    @Override
    public List<SysUser> findByUsernameIn(List<String> usernameList) {
        return baseMapper.findByUsernameIn(usernameList);
    }

    @Override
    @Transactional
    @CacheEvict(key ="'findByUsernameOrPhone_'+#user.username+'_'+#user.phone")
    public SysUser save(User user) {
        String roleIds = user.getRoleIds();
        String departIds = user.getDepartIds();

        SysUser sysUser = SysUser.builder().build();
        BeanUtils.copyProperties(user, sysUser);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(password);
        boolean res = save(sysUser);

        String[] idsArry = StrUtil.split(departIds, ",");
        List<String> idsList = Arrays.asList(idsArry);
        boolean res1 = sysUserDepartService.saveBatch(idsList, sysUser.getId());

        idsArry = StrUtil.split(roleIds, ",");
        idsList = Arrays.asList(idsArry);
        boolean res2 = sysUserRoleService.saveBatch(idsList, sysUser.getId());

        if (res && res1 && res2) {
            return sysUser;
        }
        return null;
    }
}
