package ga.baocai.adal.web.mapper;

import lombok.extern.slf4j.Slf4j;
import ga.baocai.adal.web.AdalWebApplicationTests;
import ga.baocai.adal.web.entity.SysPermission;
import ga.baocai.adal.web.entity.SysRole;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.service.SysPermissionService;
import ga.baocai.adal.web.service.SysRoleService;
import ga.baocai.adal.web.service.SysUserService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * UserDao 测试
 * </p>
 */
@Slf4j
public class SysUserDaoTest extends AdalWebApplicationTests {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysPermissionService permissionService;

    @Test
    public void findByUsernameIn() {
        List<String> usernameList = Lists.newArrayList("admin", "user");
        List<SysUser> userList = sysUserService.findByUsernameIn(usernameList);
        Assert.assertEquals(2, userList.size());
        log.info("【userList】= {}", userList);
    }

    @Test
    public void findByUsernameOrEmailOrPhone() {
        Optional<SysUser> user = sysUserService.findByUsernameOrPhone("admin", null);
        Assert.assertEquals(true, user.isPresent());
        log.info("【user】= {}", user.get());
    }

    @Test
    public void userPermission() {
        Optional<SysUser> user = sysUserService.findByUsernameOrPhone("admin", null);
        List<SysRole> roles = sysRoleService.getRolesByUserId(user.get().getId());
        log.info("【user roles】= {}", roles);
        List<SysPermission> permissions = permissionService.getPermissionsByRoleId(roles.get(0).getId());
//        List<List<SysPermission>> permissions1 = roles.stream().map(sysRole -> {
//                    String roleId = sysRole.getId();
//                    return permissionService.getPermissionsByRoleId(roleId);
//                }).collect(Collectors.toList());
//        List<SysPermission> permissions = roles.stream().map(sysRole -> {
//            String roleId = sysRole.getId();
//            return permissionService.getPermissionsByRoleId(roleId);
//        }).flatMap(Collection::stream).collect(Collectors.toSet()).stream().collect(Collectors.toList());
        log.info("【user permissions】= {}", permissions);
    }

    @Test
    public void testSelect() {
        List<SysUser> userList = sysUserService.list();
        Assert.assertEquals(true, userList.size() > 0);
        log.info("【userList】= {}", userList);
    }

    @Test
    public void addUser() {
        LocalDateTime date = LocalDateTime.now();

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = bCryptPasswordEncoder.encode("123456");

        SysUser sysUser = SysUser.builder().
                username("baocai").
                password(password).
                birthday(LocalDate.now()).
                createTime(date).
                updateTime(date).
                build();
        boolean res = sysUserService.save(sysUser);
        log.info("【addUser】= {}", res);
    }
}