package ga.baocai.adal.web.controller;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.SysPermission;
import ga.baocai.adal.web.result.PermissionData;
import ga.baocai.adal.web.result.SysPermissionTree;
import ga.baocai.adal.web.playload.Permission;
import ga.baocai.adal.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@RestController
@Slf4j
@RequestMapping("/api/sys/permission")
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @ApiOperation("数据节点")
    @GetMapping(value = "/queryTreeList")
    public CommonResponse queryTreeList() {
        List<SysPermissionTree> permissionList = sysPermissionService.queryTreeList();
        return CommonResponse.ofSuccess(permissionList);
    }

    @ApiOperation("查询用户拥有的菜单权限和按钮权限")
    @GetMapping(value = "/getUserPermission")
    public CommonResponse getUserPermission() {
        String userId = getUserId();
        PermissionData sysPermissions = sysPermissionService.getUserPermission(userId);
        if (sysPermissions!=null) {
            return CommonResponse.ofSuccess(sysPermissions);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/add")
    public CommonResponse add(@RequestBody Permission permission) {
        String userId = getUserId();
        SysPermission sysPermission = sysPermissionService.savePermission(permission, userId);
        if (sysPermission!=null) {
            return CommonResponse.ofSuccess(sysPermission);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @GetMapping("/info")
    public CommonResponse info(@RequestParam(name="id") String id) {
        SysPermission sysPermission = sysPermissionService.getById(id);
        if (sysPermission!=null) {
            return CommonResponse.ofSuccess(sysPermission);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/edit")
    public CommonResponse edit(@RequestBody Permission permission) {
        String userId = getUserId();
        SysPermission sysPermission = sysPermissionService.updatePermissionById(permission, userId);
        if (sysPermission!=null) {
            return CommonResponse.ofSuccess(sysPermission);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/delete")
    public CommonResponse delete(@RequestBody Permission permission) {
        String id = permission.getId();
        if (StrUtil.isEmpty(id)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        SysPermission sysPermission = sysPermissionService.getById(id);
        if (null == sysPermission) {
            return CommonResponse.ofMessage("未找到对应实体");
        }
        boolean hasChildren = sysPermissionService.hasChildren(permission);
        if (hasChildren) {
            JSONObject x = new JSONObject();
            x.put("hasChildren", true);
            return CommonResponse.ofSuccess(x);
        }
        if (sysPermissionService.delete(id)) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @PostMapping("/deleteForce")
    public CommonResponse deleteForce(@RequestBody Permission permission) {
        String id = permission.getId();
        if (StrUtil.isEmpty(id)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        SysPermission sysPermission = sysPermissionService.getById(id);
        if (null == sysPermission) {
            return CommonResponse.ofMessage("未找到对应实体");
        }

        if (sysPermissionService.deleteForce(permission)) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}

