package me.baocai.adal.web.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.entity.SysApi;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.playload.Api;
import me.baocai.adal.web.playload.Role;
import me.baocai.adal.web.service.SysApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-21
 */
@RestController
@RequestMapping("/api/sys/api")
public class SysApiController {

    @Autowired
    private SysApiService sysApiService;

    @ApiOperation("保存接口")
    @ResponseBody
    @PostMapping("/save")
    public CommonResponse save(@RequestBody Api api) {
        SysApi sysApi = sysApiService.save(api);
        if (null != sysApi) {
            return CommonResponse.ofSuccess(sysApi);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("保存接口")
    @ResponseBody
    @PostMapping("/edit")
    public CommonResponse edit(@RequestBody Api api) {
        SysApi sysApi = sysApiService.edit(api);
        if (null != sysApi) {
            return CommonResponse.ofSuccess(sysApi);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }

    @ApiOperation("接口列表")
    @ResponseBody
    @GetMapping("/list")
    public CommonResponse list(Api api, @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                               @RequestParam(name="pageSize", defaultValue="10") Integer pageSize) {
        Page<SysApi> page = new Page<SysApi>(pageNo, pageSize);
        IPage<SysApi> sysRoles = sysApiService.list(api, page);
        if (null != sysRoles) {
            return CommonResponse.ofSuccess(sysRoles);
        }
        return CommonResponse.ofStatus(Status.ERROR);
    }
}

