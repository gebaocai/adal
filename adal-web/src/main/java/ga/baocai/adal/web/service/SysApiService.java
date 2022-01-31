package ga.baocai.adal.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ga.baocai.adal.web.entity.SysApi;
import ga.baocai.adal.web.model.SysApiData;
import ga.baocai.adal.web.playload.Api;
import com.baomidou.mybatisplus.extension.service.IService;

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

    List<SysApiData> queryTreeList();
}
