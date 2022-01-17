package me.baocai.adal.web.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysRolePermission;
import me.baocai.adal.web.playload.DepartPermission;
import me.baocai.adal.web.playload.Role;
import me.baocai.adal.web.service.SysDepartPermissionService;
import me.baocai.adal.web.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/api/sys/rolePermission")
public class SysRolePermissionController extends BaseController {
    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @ApiOperation("角色列表接口")
    @ResponseBody
    @GetMapping("/list")
    public CommonResponse list(String roleId) {
        List<String> permissionIds = sysRolePermissionService.list(roleId);
        if (null != permissionIds) {
            return CommonResponse.ofSuccess(permissionIds);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}

