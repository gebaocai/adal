package me.baocai.adal.web.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.entity.SysPermission;
import me.baocai.adal.web.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/sys/permission")
public class SysPermissionController extends BaseController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @ApiOperation("数据节点")
    @GetMapping(value = "/list")
    public CommonResponse list() {
        List<SysPermission> permissionList = sysPermissionService.list();
        return CommonResponse.ofSuccess(permissionList);
    }

    @ApiOperation("查询用户拥有的菜单权限和按钮权限")
    @GetMapping(value = "/getUserPermission")
    public CommonResponse getUserPermission() {
        String userId = getUserId();

        return CommonResponse.ofSuccess();
    }
}

