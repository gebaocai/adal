package me.baocai.adal.web.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.service.SysUserDepartService;
import me.baocai.adal.web.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户部门关系表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2021-08-19
 */
@Api(tags = "用户部门接口")
@RestController
@RequestMapping("/sys/userDepart")
public class SysUserDepartController {
    @Autowired
    private SysUserDepartService sysUserDepartService;

    @ApiOperation("批量保存部门接口")
    @ResponseBody
    @PostMapping("/batchSave")
    public CommonResponse batchSave(@RequestParam("userId") String userId, @RequestParam("departIds") String departIds) {
        String[] idsArry = StrUtil.split(departIds, ",");
        List<String> idsList = Arrays.asList(idsArry);
        if (StrUtil.isEmpty(userId) || CollUtil.isEmpty(idsList)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        boolean result = sysUserDepartService.saveBatch(idsList, userId);
        if (!result) {
            return CommonResponse.ofStatus(Status.ERROR);
        }
        return CommonResponse.ofSuccess();
    }
}

