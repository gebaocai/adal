package me.baocai.adal.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysUser;
import me.baocai.adal.web.playload.User;
import me.baocai.adal.web.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @author gebaocai
 * @since 2021-8-21
 */
@RestController
@RequestMapping("/sys/user")
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
}
