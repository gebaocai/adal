package me.baocai.adal.web.controller;


import cn.hutool.json.JSONObject;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.playload.DepartPermission;
import me.baocai.adal.web.service.SysDepartPermissionService;
import me.baocai.adal.web.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * <p>
 * 角色权限表 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/sys/rolePermission")
public class SysRolePermissionController extends BaseController {

}

