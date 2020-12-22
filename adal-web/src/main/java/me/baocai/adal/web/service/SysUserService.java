package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.model.SysUser;

import java.util.List;
import java.util.Optional;

public interface SysUserService extends IService<SysUser> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param email    邮箱
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<SysUser> findByUsernameOrEmailOrPhone(String username, String email,
                                                   String phone);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<SysUser> findByUsernameIn(List<String> usernameList);
}
