package ga.baocai.adal.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ga.baocai.adal.web.playload.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @ApiOperation("修改用户密码")
    @ResponseBody
    @PostMapping("/changePassword")
    public CommonResponse changePassword(@RequestBody User user) {
        SysUser existedSysUser = sysUserService.getById(user.getId());
        if (null == existedSysUser) {
            return CommonResponse.ofStatus(Status.REQUEST_NOT_FOUND);
        }

        SysUser sysUser = sysUserService.changePassword(user);
        if (null != sysUser) {
            return CommonResponse.ofSuccess(sysUser);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("用户列表接口")
    @ResponseBody
    @GetMapping("/list")
    public CommonResponse list(User user, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        Page page = new Page<>(pageNo, pageSize);
        IPage<User> sysUsers = sysUserService.list(user, page);
        if (null != sysUsers) {
            return CommonResponse.ofSuccess(sysUsers);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("用户列表接口")
    @ResponseBody
    @GetMapping("/listByRoleId")
    public CommonResponse list(String roleId) {
        List<User> sysUsers = sysUserService.listByRoleId(roleId);
        if (null != sysUsers) {
            return CommonResponse.ofSuccess(sysUsers);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @GetMapping("/info")
    public CommonResponse info(@RequestParam(name="id") String id) {
        SysUser sysUser = sysUserService.getById(id);
        if (sysUser!=null) {
            return CommonResponse.ofSuccess(sysUser);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}