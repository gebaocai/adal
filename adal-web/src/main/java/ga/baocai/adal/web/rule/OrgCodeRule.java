package ga.baocai.adal.web.rule;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.service.SysDepartService;
import me.baocai.common.util.YouBianCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gebaocai
 * @date 2020/12/26 13:54
 */
@Component
public class OrgCodeRule {

    @Autowired
    private SysDepartService sysDepartService;

    public String[] getOrgCode(String parentId) {

        //定义部门类型
        String orgType = "";
        // 定义新编码字符串
        String orgCode = "";
        if (StrUtil.isEmpty(parentId)) {
            List<SysDepart> departList = sysDepartService.list(new LambdaQueryWrapper<SysDepart>()
                    .eq(SysDepart::getParentId, "")
                    .orderByDesc(SysDepart::getOrgCode));
            if (null == departList || departList.size() <= 0) {
                orgCode = YouBianCodeUtil.getNextYouBianCode(null);
                orgType = "1";
            } else {
                SysDepart depart = departList.get(0);
                orgType = depart.getOrgType();
                orgCode = YouBianCodeUtil.getNextYouBianCode(depart.getOrgCode());
            }

        } else {
            List<SysDepart> departList = sysDepartService.list(new LambdaQueryWrapper<SysDepart>()
                    .eq(SysDepart::getParentId, parentId)
                    .orderByDesc(SysDepart::getOrgCode));
            SysDepart parentDepart = sysDepartService.getById(parentId);
            String parentOrgCode = parentDepart.getOrgCode();
            orgType = Convert.toStr(Convert.toInt(parentDepart.getOrgType()) + 1);
            if (null == departList || departList.size() <= 0) {
                orgCode = YouBianCodeUtil.getSubYouBianCode(parentOrgCode, null);
            } else {
                SysDepart depart = departList.get(0);
                String subCode = depart.getOrgCode();
                orgCode = YouBianCodeUtil.getSubYouBianCode(parentOrgCode, subCode);
            }
        }
        String[] strArray = {orgCode, orgType};
        return strArray;
    }
}
