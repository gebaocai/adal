package ga.baocai.adal.web.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ga.baocai.adal.web.playload.ChangePW;
import ga.baocai.adal.web.result.PermissionData;
import ga.baocai.adal.web.result.SysUserData;
import ga.baocai.adal.web.playload.User;
import ga.baocai.adal.web.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@Slf4j
public class SysUserController extends BaseController{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysPermissionService sysPermissionService;

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
    public CommonResponse changePassword(@RequestBody ChangePW changePW) {
        if (StrUtil.isAllEmpty(changePW.getId(), changePW.getCurPassword())) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        if (!StrUtil.equals(changePW.getNewPassword(), changePW.getRePassword())) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }

        if (StrUtil.isEmpty(changePW.getId())) {
            String curUserId = getUserId();
            changePW.setId(curUserId);
        }

        SysUser existedSysUser = sysUserService.getById(changePW.getId());
        if (null == existedSysUser) {
            return CommonResponse.ofStatus(Status.REQUEST_NOT_FOUND);
        }

        if (StrUtil.isNotEmpty(changePW.getCurPassword())) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            if (!bCryptPasswordEncoder.matches(changePW.getCurPassword(), existedSysUser.getPassword())) {
                log.info("curPassword not match!!!");
                return CommonResponse.ofStatus(Status.PARAM_PASSWORD_NOT_MATCH);
            }
        }

        SysUser sysUser = sysUserService.changePassword(changePW);
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

    @GetMapping("/currentUser")
    public CommonResponse currentUser() {
        SysUser sysUser = sysUserService.getById(getUserId());
        SysUserData sysUserData = new SysUserData();
        BeanUtil.copyProperties(sysUser, sysUserData);
        PermissionData sysPermissions = sysPermissionService.getUserPermission(getUserId());
        sysUserData.setPermissionData(sysPermissions);
        if (sysUserData!=null) {
            return CommonResponse.ofSuccess(sysUserData);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}
