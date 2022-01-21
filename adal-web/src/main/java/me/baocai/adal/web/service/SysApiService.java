package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.baocai.adal.web.entity.SysApi;
import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.playload.Api;
import me.baocai.adal.web.playload.Role;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-21
 */
public interface SysApiService extends IService<SysApi> {
    SysApi save(Api api);

    SysApi edit(Api api);

    IPage<SysApi> list(Api api, Page<SysApi> page);

    List<SysApi> listAll();
}
