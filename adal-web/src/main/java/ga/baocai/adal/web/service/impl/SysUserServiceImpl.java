package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.entity.SysRole;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.playload.User;
import ga.baocai.adal.web.mapper.SysUserDao;
import ga.baocai.adal.web.service.*;
import ga.baocai.adal.web.util.DataScopeUtil;
import ga.baocai.adal.web.vo.UserPrincipal;
import org.apache.logging.log4j.util.Strings;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserDepartService sysUserDepartService;
    @Autowired
    private SysDepartService sysDepartService;
    @Autowired
    private Enforcer enforcer;

    @Override
//    @Cacheable(key ="'findByUsernameOrPhone_'+#username+'_'+#phone")
    public Optional<SysUser> findByUsernameOrPhone(String username, String phone) {
        SysUser sysUser = getOne(lambdaQuery().eq(Strings.isNotEmpty(username), SysUser::getUsername, username).
                or().eq(Strings.isNotEmpty(phone), SysUser::getPhone, phone).getWrapper());
        return Optional.ofNullable(sysUser);
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
        //Set DataScope in Sql
        DataScopeUtil.queryWrapper(queryWrapper);
        queryWrapper.ne("user_type", Consts.ADMIN);

        IPage<SysUser> userIPage = page(page, queryWrapper);
        final List<String> ids = CollUtil.newArrayList();
        IPage<User> userIPage1 = userIPage.convert(x-> {
            User u = new User();
            BeanUtils.copyProperties(x, u);
            List<String> departIds = sysUserDepartService.listByUserId(x.getId());
            String departIdsStr = StrUtil.join(",", departIds);
            u.setDepartIds(departIdsStr);
            u.setPassword("");
            ids.addAll(departIds);
            return u;
        });


        List<String> departIds = ids.stream().distinct().collect(Collectors.toList());
        List<SysDepart> sysDeparts = CollUtil.isEmpty(departIds)?CollUtil.newArrayList():sysDepartService.listByIds(departIds);
        Map<String, String> departMap = new HashMap<>();
        sysDeparts.stream().forEach(x-> {
            departMap.put(x.getId(), x.getDepartName());
        });

        userIPage1.getRecords().stream().forEach(x->{
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
                x.setDepartNames(sb.toString());
            }
        });

        return userIPage1;
    }

    @Override
    public List<User> listByRoleId(String roleId) {
        List<String> userIds = enforcer.getUsersForRole(Consts.CASBIN_ROLE_KEY_PREFIX + roleId);
        if (CollUtil.isEmpty(userIds)) {
            return CollUtil.newArrayList();
        }
        List<SysUser> users = listByIds(userIds);

        final List<String> ids = CollUtil.newArrayList();
        List<User> usersRes = users.stream().map(x -> {
            User u = new User();
            BeanUtils.copyProperties(x, u);
            List<String> departIds = sysUserDepartService.listByUserId(x.getId());
            String departIdsStr = StrUtil.join(",", departIds);
            u.setDepartIds(departIdsStr);
            u.setPassword("");
            ids.addAll(departIds);
            return u;
        }).collect(Collectors.toList());
        List<String> departIds = ids.stream().distinct().collect(Collectors.toList());
        List<SysDepart> sysDeparts = CollUtil.isEmpty(departIds) ? CollUtil.newArrayList() : sysDepartService.listByIds(departIds);
        Map<String, String> departMap = new HashMap<>();
        sysDeparts.stream().forEach(x -> {
            departMap.put(x.getId(), x.getDepartName());
        });
        usersRes.stream().forEach(x -> {
            String ids1 = x.getDepartIds();
            if (!StrUtil.isEmpty(ids1)) {
                String[] idsArry = ids1.split(",");
                StrBuilder sb = new StrBuilder();
                for (String id : idsArry) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(departMap.get(id));
                }
                x.setDepartNames(sb.toString());
            }
        });
        return usersRes;
    }

}
