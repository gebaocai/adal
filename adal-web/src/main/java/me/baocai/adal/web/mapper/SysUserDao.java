package me.baocai.adal.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.baocai.adal.web.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户 DAO
 * </p>
 */
@Component
public interface SysUserDao extends BaseMapper<SysUser> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param phone    手机号
     * @return 用户信息
     */
    Optional<SysUser> findByUsernameOrPhone(@Param("username") String username, @Param("phone") String phone);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<SysUser> findByUsernameIn(@Param("usernameList") List<String> usernameList);
}
