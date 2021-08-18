package me.baocai.adal.web.controller;


import cn.hutool.core.util.StrUtil;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.model.SysDepartTreeModel;
import me.baocai.adal.web.playload.Depart;
import me.baocai.adal.web.service.SysDepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 组织机构表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/sys/sysDepart")
public class SysDepartController extends BaseController {

    @Autowired
    private SysDepartService sysDepartService;

    @GetMapping("/list")
    public CommonResponse list() {
        List<SysDepartTreeModel> treeModelList = sysDepartService.queryTreeList();
        return CommonResponse.ofSuccess(treeModelList);
    }

    @PostMapping("/add")
    public CommonResponse add(@RequestBody Depart depart) {
        String userId = getUserId();
        SysDepart sysDepart = sysDepartService.saveDepartData(depart, userId);
        if (sysDepart!=null) {
            return CommonResponse.ofSuccess(sysDepart);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@RequestBody Depart depart) {
        String userId = getUserId();
        SysDepart sysDepart = sysDepartService.updateDepartDataById(depart, userId);
        if (sysDepart!=null) {
            return CommonResponse.ofSuccess(sysDepart);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody Depart depart) {
        String id = depart.getId();
        if (StrUtil.isEmpty(id)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        SysDepart sysDepart = sysDepartService.getById(id);
        if (null == sysDepart) {
            return CommonResponse.ofMessage("未找到对应实体");
        }
        if (sysDepartService.delete(id)) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}

