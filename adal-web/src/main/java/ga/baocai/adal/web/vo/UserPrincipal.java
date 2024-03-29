package ga.baocai.adal.web.vo;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysUser;
import lombok.Data;
import ga.baocai.adal.web.entity.SysApi;
import org.springframework.beans.BeanUtils;
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
     * 性别，admin-1，用户-0
     */
    private Integer userType;

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
     * 数据类型
     */
    private Integer dataScopeType;

    /**
     * 数据列表
     */
    private List<String> dataScope;

    /**
     * 用户角色列表
     */
    private List<String> roles;

    /**
     * 用户权限列表
     */
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(SysUser user, List<SysApi> apiList, Integer dataScopeType, List<String> dataScope) {
        UserPrincipal userPrincipal = new UserPrincipal();
        BeanUtils.copyProperties(user, userPrincipal);
        userPrincipal.setDataScopeType(dataScopeType);
        userPrincipal.setDataScope(dataScope);

        List<GrantedAuthority> authorities = apiList.stream()
                .filter(api -> StrUtil.isNotBlank(api.getUrl()))
                .map(api -> new SimpleGrantedAuthority(api.getUrl()))
                .collect(Collectors.toList());
        userPrincipal.setAuthorities(authorities);
        return userPrincipal;
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