package me.baocai.adal.web.controller;


import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysDepart;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.model.SysPermissionTree;
import me.baocai.adal.web.playload.Depart;
import me.baocai.adal.web.playload.Permission;
import me.baocai.adal.web.service.SysPermissionService;
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
        List<SysPermission> sysPermissions = sysPermissionService.getUserPermission(userId);
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
        if (sysPermissionService.delete(id)) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}

