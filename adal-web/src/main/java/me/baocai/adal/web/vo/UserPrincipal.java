package me.baocai.adal.web.vo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.baocai.adal.web.common.Consts;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义User
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 8701647944963022922L;
    /**
     * 主键
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别，男-1，女-2
     */
    private Integer sex;

    /**
     * 状态，启用-1，禁用-0
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 用户角色列表
     */
    private List<String> roles;

    /**
     * 用户权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(SysUser user, List<SysRole> roles, List<SysPermission> permissions) {
        List<String> roleNames = roles.stream()
                .map(SysRole::getRoleName)
                .collect(Collectors.toList());

        List<GrantedAuthority> authorities = permissions.stream()
                .filter(permission -> StrUtil.isNotBlank(permission.getPerms()))
                .map(permission -> new SimpleGrantedAuthority(permission.getPerms()))
                .collect(Collectors.toList());

        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(), user.getRealname(), user.getPhone(), user.getEmail(), user.getBirthday(), user.getSex(), user.getStatus(), user.getCreateTime(), user.getUpdateTime(), roleNames, authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Objects.equals(this.status, Consts.ENABLE);
    }
}