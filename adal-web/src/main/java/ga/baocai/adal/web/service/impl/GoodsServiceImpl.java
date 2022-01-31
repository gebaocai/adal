package ga.baocai.adal.web.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ga.baocai.adal.web.entity.Goods;
import ga.baocai.adal.web.mapper.GoodsDao;
import ga.baocai.adal.web.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gebaocai
 * @since 2020-05-02
 */
@DS("slave")
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Override
    public IPage<Goods> selectUserPage(Page<Goods> page, Integer state) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return baseMapper.selectPageVo(page, state);
    }

    @DS("master")
    @Override
    public boolean addGoods(Goods goods) {
        return save(goods);
    }
}
