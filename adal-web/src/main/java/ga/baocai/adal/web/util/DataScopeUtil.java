package ga.baocai.adal.web.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.vo.UserPrincipal;

import java.util.List;

public class DataScopeUtil {

    public static void queryWrapper(QueryWrapper<SysUser> queryWrapper) {
        UserPrincipal principal = SecurityUtil.getCurrentUser();
        Integer dataScopeType = principal.getDataScopeType();
        List<String> dataScope = principal.getDataScope();
        if (Consts.ADMIN != principal.getUserType()) {
            if (Consts.DATA_SCOPE_SELF == dataScopeType) {
                queryWrapper.eq("create_user", principal.getId());
            } else {
                StringBuilder sb = new StringBuilder();
                dataScope.stream().forEach(x->{
                    if (sb.length()>0) {
                        sb.append(",");
                    } else {
                        sb.append("(");
                    }
                    sb.append(x);
                });
                sb.append(")");
                queryWrapper.inSql("id", "select user_id from sys_user_depart where depart_id in " + sb.toString());
            }
        }
    }

}
