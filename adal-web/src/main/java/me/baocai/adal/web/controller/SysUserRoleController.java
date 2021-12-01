package me.baocai.adal.web.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Consts;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysUserRole;
import me.baocai.adal.web.playload.LoginRequest;
import me.baocai.adal.web.service.SysUserRoleService;
import me.baocai.adal.web.vo.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户角色关系表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2021-08-19
 */
@Api(tags = "用户角色接口")
@RestController
@RequestMapping("/api/sys/userRole")
public class SysUserRoleController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @ApiOperation("批量保存角色接口")
    @PostMapping("/batchSave")
    public CommonResponse batchSave(@RequestParam("userId") String userId, @RequestParam("roleIds") String roleIds) {
        String[] idsArry = StrUtil.split(roleIds, ",");
        List<String> idsList = Arrays.asList(idsArry);
        if (StrUtil.isEmpty(userId) || CollUtil.isEmpty(idsList)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        boolean result = sysUserRoleService.saveBatch(idsList, userId);
        if (!result) {
            return CommonResponse.ofStatus(Status.ERROR);
        }
        return CommonResponse.ofSuccess();
    }

    @ApiOperation("角色接口")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam("userId") String userId) {
        if (StrUtil.isEmpty(userId)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        List<SysUserRole> roleList = sysUserRoleService.listByUserId(userId);
        List<String> roleStrList = roleList.stream().map(x->x.getRoleId()).collect(Collectors.toList());
        return CommonResponse.ofSuccess(roleStrList);
    }

}

