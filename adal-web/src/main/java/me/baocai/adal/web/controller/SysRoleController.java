package me.baocai.adal.web.controller;


import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysUser;
import me.baocai.adal.web.playload.Role;
import me.baocai.adal.web.playload.User;
import me.baocai.adal.web.service.SysRoleService;
import me.baocai.adal.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("保存角色接口")
    @ResponseBody
    @PostMapping("/save")
    public CommonResponse save(@RequestBody Role role) {
        Optional<SysRole> existRole = sysRoleService.getRolesByName(role.getName());
        if (existRole.isPresent()) {
            return CommonResponse.ofStatus(Status.PARAM_EXIST);
        }

        SysRole sysRole = sysRoleService.save(role);
        if (null != sysRole) {
            return CommonResponse.ofSuccess(sysRole);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("角色列表接口")
    @ResponseBody
    @PostMapping("/list")
    public CommonResponse list(@RequestBody Role role, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        Page<SysRole> page = new Page<SysRole>(pageNo, pageSize);
        IPage<SysRole> sysRoles = sysRoleService.list(role, page);
        if (null != sysRoles) {
            return CommonResponse.ofSuccess(sysRoles);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

//    @ApiOperation("保存角色关联用户")
//    @ResponseBody
//    @PostMapping("/saveUser")
//    public CommonResponse saveUser(@RequestBody Role role, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
//                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
//        Page<SysRole> page = new Page<SysRole>(pageNo, pageSize);
//        IPage<SysRole> sysRoles = sysRoleService.list(role, page);
//        if (null != sysRoles) {
//            return CommonResponse.ofSuccess(sysRoles);
//        }
//        return CommonResponse.ofStatus(Status.ERROR);
//    }

    @ApiOperation("保存角色权限")
    @ResponseBody
    @PostMapping("/savePermission")
    public CommonResponse savePermission(@RequestBody JSONObject jsonObject) {
        String roldId = jsonObject.getStr("roldId");
        String permissionIds = jsonObject.getStr("permissionIds");

        return CommonResponse.ofStatus(Status.ERROR);
    }
}

