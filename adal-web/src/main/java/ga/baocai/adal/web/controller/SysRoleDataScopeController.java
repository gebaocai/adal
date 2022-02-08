package ga.baocai.adal.web.controller;


import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.SysRole;
import ga.baocai.adal.web.model.RoleDataScopeData;
import ga.baocai.adal.web.playload.RoleDataScope;
import ga.baocai.adal.web.playload.UserDataScope;
import ga.baocai.adal.web.service.SysRoleDataScopeService;
import ga.baocai.adal.web.service.SysRoleService;
import ga.baocai.adal.web.service.SysUserDataScopeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统角色数据范围表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
@RestController
@RequestMapping("/sys-role-data-scope")
public class SysRoleDataScopeController {
    @Autowired
    private SysRoleDataScopeService sysRoleDataScopeService;
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("编辑用户DataScope接口")
    @ResponseBody
    @PostMapping("/batchSave")
    public CommonResponse batchSave(@RequestBody RoleDataScope roleDataScope) {
        boolean result = sysRoleDataScopeService.saveRoleDataScope(roleDataScope);
        if (result) {
            return CommonResponse.ofSuccess();
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ResponseBody
    @GetMapping("/list")
    public CommonResponse list(String roleId) {
        SysRole sysRole = sysRoleService.getById(roleId);
        RoleDataScopeData dataScope = new RoleDataScopeData();
        dataScope.setDataScopeType(sysRole.getDataScopeType());
        if (Consts.DATA_SCOPE_CUSTOM == sysRole.getDataScopeType()) {
            List<String> departIds = sysRoleDataScopeService.list(roleId);
            dataScope.setDepartIds(departIds);
            dataScope.setRoleId(roleId);
        }
        return CommonResponse.ofSuccess(dataScope);
    }
}

