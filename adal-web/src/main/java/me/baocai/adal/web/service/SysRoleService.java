package me.baocai.adal.web.service;

import me.baocai.adal.web.entity.SysRole;

import java.util.List;

public interface SysRoleService {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> getRolesByUserId(String userId);
}
