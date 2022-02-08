package ga.baocai.adal.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ga.baocai.adal.web.entity.SysRole;
import ga.baocai.adal.web.playload.Role;

import java.util.List;
import java.util.Optional;

public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<SysRole> getRolesByUserId(String userId);

    Optional<SysRole> getRolesByName(String name);

    SysRole save(Role role);

    SysRole edit(Role role);

    IPage<SysRole> list(Role role, Page<SysRole> page);

    List<SysRole> listAll();
}
