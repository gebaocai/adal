package me.baocai.adal.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.baocai.adal.web.entity.Goods;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author gebaocai
 * @since 2020-05-02
 */
public interface GoodsService extends IService<Goods> {

    IPage<Goods> selectUserPage(Page<Goods> page, Integer state);


    boolean addGoods(Goods goods);

}
