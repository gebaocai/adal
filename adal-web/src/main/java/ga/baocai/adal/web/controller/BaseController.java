package ga.baocai.adal.web.controller;

import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.vo.UserPrincipal;
import org.casbin.jcasbin.main.Enforcer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

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
