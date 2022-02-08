package ga.baocai.adal.web.controller;


import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.playload.DepartPermission;
import ga.baocai.adal.web.playload.UserDataScope;
import ga.baocai.adal.web.service.SysUserDataScopeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 系统用户数据范围表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
@RestController
@RequestMapping("/api/sys-user-data-scope/")
public class SysUserDataScopeController extends BaseController{
    @Autowired
    private SysUserDataScopeService sysUserDataScopeService;

    @ApiOperation("编辑用户DataScope接口")
    @ResponseBody
    @PostMapping("/batchSave")
    public CommonResponse batchSave(@RequestBody UserDataScope userDataScope) {
        boolean result = sysUserDataScopeService.saveUserDataScope(userDataScope);
        if (result) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ResponseBody
    @GetMapping("/list")
    public CommonResponse list(String departId) {
        List<String> permissionIds = sysUserDataScopeService.list(departId);
        if (null != permissionIds) {
            return CommonResponse.ofSuccess(permissionIds);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}

