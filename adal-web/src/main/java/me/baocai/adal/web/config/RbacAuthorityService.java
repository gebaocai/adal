package me.baocai.adal.web.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import me.baocai.adal.web.common.Consts;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysUserDepart;
import me.baocai.adal.web.entity.SysUserRole;
import me.baocai.adal.web.exception.SecurityException;
import me.baocai.adal.web.service.SysPermissionService;
import me.baocai.adal.web.service.SysRoleService;
import me.baocai.adal.web.service.SysUserDepartService;
import me.baocai.adal.web.service.SysUserRoleService;
import me.baocai.adal.web.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态路由认证
 * </p>
 */
@Component
public class RbacAuthorityService {
    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private RequestMappingHandlerMapping mapping;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserDepartService sysUserDepartService;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {
            UserPrincipal principal = (UserPrincipal) userInfo;
            String userId = principal.getId();

//            List<SysRole> roles = sysRoleService.getRolesByUserId(userId);
//            List<SysPermission> permissions = roles.stream().map(sysRole -> {
//                String roleId = sysRole.getId();
//                return permissionService.getPermissionsByRoleId(roleId);
//            }).flatMap(Collection::stream).collect(Collectors.toSet()).stream().collect(Collectors.toList());

//            List<SysUserRole> userRoles = sysUserRoleService.listByUserId(userId);
//            List<String> roleIds = userRoles.stream().map(userRole->userRole.getRoleId()).collect(Collectors.toList());
//
//            List<SysUserDepart> userDeparts = sysUserDepartService.listByUserId(userId);
//            List<String> departIds = userDeparts.stream().map(userDepart->userDepart.getDepartId()).collect(Collectors.toList());
//
//            List<SysPermission> permissions = permissionService.getPermissionsByDepartIds(departIds);
//            List<SysPermission> permissions2 = permissionService.getPermissionsByRoleIds(roleIds);
//
//            permissions.removeAll(permissions2);
//            permissions.addAll(permissions2);
//
//            //获取资源，前后端分离，所以过滤页面权限，只保留按钮权限
//            List<SysPermission> btnPerms = permissions.stream()
//                    // 过滤页面权限
//                    .filter(permission -> Objects.equals(permission.getMenuType(), Consts.BUTTON))
//                    // 过滤 URL 为空
//                    .filter(permission -> StrUtil.isNotBlank(permission.getUrl()))
//                    .collect(Collectors.toList());
//
//            for (SysPermission btnPerm : btnPerms) {
//                AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(btnPerm.getUrl());
//                if (antPathMatcher.matches(request)) {
//                    hasPermission = true;
//                    break;
//                }
//            }
            hasPermission = true;
            return hasPermission;
        } else {
            return false;
        }
    }

    /**
     * 校验请求是否存在
     *
     * @param request 请求
     */
    private void checkRequest(HttpServletRequest request) {
        // 获取当前 request 的方法
        String currentMethod = request.getMethod();
        HashMap<String, List<String>> urlMapping = allUrlMapping();

        for (String uri : urlMapping.keySet()) {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request)) {
                if (!urlMapping.get(uri)
                        .contains(currentMethod)) {
                    throw new SecurityException(Status.HTTP_BAD_METHOD);
                } else {
                    return;
                }
            }
        }

        throw new SecurityException(Status.REQUEST_NOT_FOUND);
    }

    /**
     * 获取 所有URL Mapping，返回格式为{"/test":["GET","POST"],"/sys":["GET","DELETE"]}
     *
     * @return {@link HashMap<String, List<String>} 格式的 URL Mapping
     */
    private HashMap<String, List<String>> allUrlMapping() {
        HashMap<String, List<String>> urlMapping = CollUtil.newHashMap();

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((k, v) -> {
            // 获取当前 key 下的获取所有URL
            Set<String> url = k.getPatternsCondition()
                    .getPatterns();
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            // 为每个URL添加所有的请求方法
            url.forEach(s -> urlMapping.put(s, method.getMethods()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.toList())));
        });

        return urlMapping;
    }
}