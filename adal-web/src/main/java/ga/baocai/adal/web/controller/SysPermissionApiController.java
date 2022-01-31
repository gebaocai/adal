package ga.baocai.adal.web.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.playload.PermissionApi;
import ga.baocai.adal.web.service.SysPermissionApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户角色关系表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/api/sys/permissionApi")
public class SysPermissionApiController {
    @Autowired
    private SysPermissionApiService sysPermissionApiService;

    @ApiOperation("批量保存角色接口")
    @PostMapping("/batchSave")
    public CommonResponse batchSave(@RequestBody PermissionApi permissionApi) {
        String[] idsArry = StrUtil.split(permissionApi.getApiIds(), ",");
        List<String> idsList = Arrays.asList(idsArry);
        if (StrUtil.isEmpty(permissionApi.getPermissionId()) || CollUtil.isEmpty(idsList)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        boolean result = sysPermissionApiService.saveBatch(idsList, permissionApi.getPermissionId());
        if (!result) {
            return CommonResponse.ofStatus(Status.ERROR);
        }
        return CommonResponse.ofSuccess();
    }

//    @ApiOperation("角色接口")
//    @GetMapping("/list")
//    public CommonResponse list(@RequestParam("permissionId") String permissionId) {
//        if (StrUtil.isEmpty(permissionId)) {
//            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
//        }
//        List<SysApi> apiList = sysPermissionApiService.list(permissionId);
////        List<String> roleStrList = roleList.stream().map(x->x.getRoleId()).collect(Collectors.toList());
//        return CommonResponse.ofSuccess(apiList);
//    }

    @ApiOperation("角色接口")
    @GetMapping("/list")
    public CommonResponse list(@RequestParam("permissionId") String permissionId) {
        if (StrUtil.isEmpty(permissionId)) {
            return CommonResponse.ofStatus(Status.PARAM_NOT_MATCH);
        }
        List<String> apiList = sysPermissionApiService.list(permissionId);
//        List<String> roleStrList = roleList.stream().map(x->x.getRoleId()).collect(Collectors.toList());
        return CommonResponse.ofSuccess(apiList);
    }
}

