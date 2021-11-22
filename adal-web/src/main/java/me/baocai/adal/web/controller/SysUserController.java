package me.baocai.adal.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.entity.SysUser;
import me.baocai.adal.web.playload.Role;
import me.baocai.adal.web.playload.User;
import me.baocai.adal.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author gebaocai
 * @since 2021-8-21
 */
@RestController
@RequestMapping("/api/sys/user")
@Api(tags = "用户接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperation("保存用户接口")
    @ResponseBody
    @PostMapping("/save")
    public CommonResponse save(@RequestBody User user) {
        Optional<SysUser> existUser = sysUserService.findByUsernameOrPhone(user.getUsername(), user.getPhone());
        if (existUser.isPresent()) {
            return CommonResponse.ofStatus(Status.PARAM_EXIST);
        }

        SysUser sysUser = sysUserService.save(user);
        if (null != sysUser) {
            return CommonResponse.ofSuccess(sysUser);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("编辑用户接口")
    @ResponseBody
    @PostMapping("/edit")
    public CommonResponse edit(@RequestBody User user) {
        Optional<SysUser> existUser = sysUserService.findByUsernameOrPhone(user.getUsername(), user.getPhone());
        if (!existUser.isPresent()) {
            return CommonResponse.ofStatus(Status.REQUEST_NOT_FOUND);
        }

        SysUser sysUser = sysUserService.edit(user);
        if (null != sysUser) {
            return CommonResponse.ofSuccess(sysUser);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("角色列表接口")
    @ResponseBody
    @GetMapping("/list")
    public CommonResponse list(User role, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        Page<SysUser> page = new Page<SysUser>(pageNo, pageSize);
        IPage<SysUser> sysUsers = sysUserService.list(role, page);
        if (null != sysUsers) {
            return CommonResponse.ofSuccess(sysUsers);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}
