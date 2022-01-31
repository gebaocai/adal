package ga.baocai.adal.web.controller;


import io.swagger.annotations.ApiOperation;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

