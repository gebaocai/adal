package me.baocai.adal.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.baocai.adal.web.entity.SysDepartPermission;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.mapper.SysRoleDao;
import me.baocai.adal.web.mapper.SysUserDepartDao;
import me.baocai.adal.web.playload.DepartPermission;
import me.baocai.adal.web.playload.Role;
import me.baocai.adal.web.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@CacheConfig(cacheNames = {"roleCache"})
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Override
//    @Cacheable(keyGenerator = "customKeyGenerator")
    public List<SysRole> getRolesByUserId(String userId) {
        return sysRoleDao.getRolesByUserId(userId);
    }

    @Override
    public Optional<SysRole> getRolesByName(String name) {
        SysRole sysRole = sysRoleDao.getRolesByName(name);
        return Optional.ofNullable(sysRole);
    }

    @Override
    public SysRole save(Role role) {
        SysRole sysRole = SysRole.builder().build();
        BeanUtils.copyProperties(role, sysRole);
        if (save(sysRole)) {
           return sysRole;
        }
        return null;
    }

    @Override
    public IPage<SysRole> list(Role role, Page<SysRole> page) {
        SysRole sysRole = SysRole.builder().build();
        BeanUtils.copyProperties(role, sysRole);
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>(sysRole);
        return page(page, queryWrapper);
    }
}
