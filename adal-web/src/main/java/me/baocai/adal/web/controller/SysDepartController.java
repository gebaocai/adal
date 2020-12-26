package me.baocai.adal.web.controller;


import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.model.SysDepartTreeModel;
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
    public CommonResponse add(@RequestBody SysDepart sysDepart) {
        String userId = getUserId();
        if (sysDepartService.saveDepartData(sysDepart, userId)) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@RequestBody SysDepart sysDepart) {
        String userId = getUserId();
        if (sysDepartService.updateDepartDataById(sysDepart, userId)) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestParam(name = "id", required = true) String id) {
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

