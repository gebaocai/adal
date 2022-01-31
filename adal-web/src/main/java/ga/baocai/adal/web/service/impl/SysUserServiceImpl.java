package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.playload.User;
import me.baocai.adal.web.entity.*;
import ga.baocai.adal.web.mapper.SysUserDao;
import ga.baocai.adal.web.service.SysDepartService;
import ga.baocai.adal.web.service.SysUserDepartService;
import ga.baocai.adal.web.service.SysUserRoleService;
import ga.baocai.adal.web.service.SysUserService;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
//@CacheConfig(cacheNames = {"userCache"})
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUser> implements SysUserService {

    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserDepartService sysUserDepartService;
    @Autowired
    private SysDepartService sysDepartService;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private Enforcer enforcer;

    @Override
//    @Cacheable(key ="'findByUsernameOrPhone_'+#username+'_'+#phone")
    public Optional<SysUser> findByUsernameOrPhone(String username, String phone) {
        return baseMapper.findByUsernameOrPhone(username, phone);
    }

    @Override
    public List<SysUser> findByUsernameIn(List<String> usernameList) {
        return baseMapper.findByUsernameIn(usernameList);
    }

    @Override
    @Transactional
//    @CacheEvict(key ="'findByUsernameOrPhone_'+#user.username+'_'+#user.phone")
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

    @Override
    @Transactional
    public SysUser edit(User user) {
        String roleIds = user.getRoleIds();
        String departIds = user.getDepartIds();

        SysUser sysUser = SysUser.builder().build();
        BeanUtils.copyProperties(user, sysUser);

//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        String password = bCryptPasswordEncoder.encode(sysUser.getPassword());
//        sysUser.setPassword(password);
        boolean res = updateById(sysUser);

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

    @Override
    @Transactional
    public SysUser changePassword(User user) {
        SysUser sysUser = SysUser.builder().
                id(user.getId()).
                password(user.getPassword()).
                build();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(password);
        boolean res = updateById(sysUser);
        if (res) {
            return sysUser;
        }
        return null;
    }

    @Override
    public User info(String id) {
        SysUser sysUser = getById(id);
        if (null == sysUser) {
            return null;
        }
        User user = new User();
        BeanUtils.copyProperties(sysUser, user);
        return user;
    }

    @Override
    public IPage<User> list(User user, Page page) {
        SysUser sysUser = SysUser.builder().build();
        BeanUtils.copyProperties(user, sysUser);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>(sysUser);
        IPage<SysUser> userIPage = page(page, queryWrapper);
        final List<String> ids = CollUtil.newArrayList();
        userIPage.getRecords().stream().forEach(x->{
            List<String> departIds = sysUserDepartService.listByUserId(x.getId());
            ids.addAll(departIds);
        });
        List<String> departIds = ids.stream().distinct().collect(Collectors.toList());
        List<SysDepart> sysDeparts = CollUtil.isEmpty(departIds)?CollUtil.newArrayList():sysDepartService.listByIds(departIds);
        Map<String, String> departMap = new HashMap<>();
        sysDeparts.stream().forEach(x-> {
            departMap.put(x.getId(), x.getDepartName());
        });

        return userIPage.convert(x->{
            User u = new User();
            BeanUtils.copyProperties(x, u);
            String ids1 = x.getDepartIds();
            if (!StrUtil.isEmpty(ids1)) {
                String[] idsArry = ids1.split(",");
                StrBuilder sb = new StrBuilder();
                for (String id:idsArry) {
                    if(sb.length()>0) {
                        sb.append(",");
                    }
                    sb.append(departMap.get(id));
                }
                u.setDepartNames(sb.toString());
            }
            return u;}
        );
    }

    @Override
    public List<User> listByRoleId(String roleId) {
        List<String> userIds = enforcer.getUsersForRole(roleId);
        if (CollUtil.isEmpty(userIds)) {
            return CollUtil.newArrayList();
        }
        List<SysUser> users = listByIds(userIds);

        final List<String> ids = CollUtil.newArrayList();
        users.stream().forEach(x->{
            String departIds = x.getDepartIds();
            if (StrUtil.isEmpty(departIds)) {
                return;
            }
            String[] idsArry = departIds.split(",");
            for (String id:idsArry) {
                ids.add(id);
            }
        });
        List<String> departIds = ids.stream().distinct().collect(Collectors.toList());
        List<SysDepart> sysDeparts = CollUtil.isEmpty(departIds)?CollUtil.newArrayList():sysDepartService.listByIds(departIds);
        Map<String, String> departMap = new HashMap<>();
        sysDeparts.stream().forEach(x-> {
            departMap.put(x.getId(), x.getDepartName());
        });
        List<User> us = users.stream().map(x-> {
            User u = new User();
            BeanUtils.copyProperties(x, u);
            String ids1 = x.getDepartIds();
            if (!StrUtil.isEmpty(ids1)) {
                String[] idsArry = ids1.split(",");
                StrBuilder sb = new StrBuilder();
                for (String id:idsArry) {
                    if(sb.length()>0) {
                        sb.append(",");
                    }
                    sb.append(departMap.get(id));
                }
                u.setDepartNames(sb.toString());
            }
            return u;
        }).collect(Collectors.toList());

        return us;
    }
}
