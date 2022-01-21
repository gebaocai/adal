package me.baocai.adal.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.baocai.adal.web.entity.SysApi;
import me.baocai.adal.web.entity.SysRole;
import me.baocai.adal.web.mapper.SysApiDao;
import me.baocai.adal.web.playload.Api;
import me.baocai.adal.web.playload.Role;
import me.baocai.adal.web.service.SysApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-21
 */
@Service
public class SysApiServiceImpl extends ServiceImpl<SysApiDao, SysApi> implements SysApiService {
    @Override
    public SysApi save(Api api) {
        SysApi sysApi = SysApi.builder().build();
        BeanUtils.copyProperties(api, sysApi);
        if (save(sysApi)) {
            return sysApi;
        }
        return null;
    }

    @Override
    public SysApi edit(Api api) {
        SysApi sysApi = SysApi.builder().build();
        BeanUtils.copyProperties(api, sysApi);
        if (updateById(sysApi)) {
            return sysApi;
        }
        return null;
    }

    @Override
    public IPage<SysApi> list(Api api, Page<SysApi> page) {
        SysApi sysApi = SysApi.builder().build();
        BeanUtils.copyProperties(api, sysApi);
        QueryWrapper<SysApi> queryWrapper = new QueryWrapper<>(sysApi);
        return page(page, queryWrapper);
    }

    @Override
    public List<SysApi> listAll() {
        return list();
    }
}
