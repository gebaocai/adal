package me.baocai.adal.web.controller;


import io.swagger.annotations.Api;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.playload.DepartPermission;
import me.baocai.adal.web.service.SysDepartPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门权限表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Api(tags = "部门权限接口")
@RestController
@RequestMapping("/sys/departPermission")
public class SysDepartPermissionController extends BaseController {

    @Autowired
    private SysDepartPermissionService sysDepartPermissionService;

    @PostMapping("/batchSave")
    public CommonResponse add(@RequestBody DepartPermission departPermission) {
        String userId = getUserId();
        boolean result = sysDepartPermissionService.saveDepartPermission(departPermission, userId);
        if (result) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

}

