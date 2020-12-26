package me.baocai.adal.web.controller;

import me.baocai.adal.web.vo.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author gebaocai
 * @date 2020/12/26 10:54
 */
public class BaseController {

    protected String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object userInfo = authentication.getPrincipal();
        if (!(userInfo instanceof UserDetails)) {
            return "";
        }
        UserPrincipal principal = (UserPrincipal) userInfo;
        String userId = principal.getId();
        return userId;
    }

}
