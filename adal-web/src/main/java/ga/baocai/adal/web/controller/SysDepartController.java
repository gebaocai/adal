package ga.baocai.adal.web.controller;


import cn.hutool.core.util.StrUtil;
import ga.baocai.adal.web.playload.Depart;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.SysDepart;
import ga.baocai.adal.web.result.SysDepartTreeModel;
import ga.baocai.adal.web.service.SysDepartService;
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
@RequestMapping("/api/sys/sysDepart")
public class SysDepartController extends BaseController {

    @Autowired
    private SysDepartService sysDepartService;

    @GetMapping("/treeList")
    public CommonResponse treeList() {
        List<SysDepartTreeModel> treeModelList = sysDepartService.queryTreeList();
        return CommonResponse.ofSuccess(treeModelList);
    }

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

    @GetMapping("/info")
    public CommonResponse info(@RequestParam(name="id") String id) {
        SysDepart sysDepart = sysDepartService.getById(id);
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

    @GetMapping("/export")
    public void export() {
        sysDepartService.export();
    }
}

