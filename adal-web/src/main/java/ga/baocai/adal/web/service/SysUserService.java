package ga.baocai.adal.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.playload.ChangePW;
import ga.baocai.adal.web.playload.User;
import ga.baocai.adal.web.entity.SysUser;

import java.util.List;
import java.util.Optional;

public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<SysUser> findByUsernameOrPhone(String username, String phone);

    SysUser save(User user);

    SysUser edit(User user);

    SysUser changePassword(ChangePW changePW);

    IPage<User> list(User user, Page<SysUser> page);

    List<User> listByRoleId(String roleId);

    User info(String id);
}
