package me.baocai.adal.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import me.baocai.adal.web.entity.Goods;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author gebaocai
 * @since 2020-05-02
 */
public interface GoodsDao extends BaseMapper<Goods> {

    IPage<Goods> selectPageVo(Page<?> page, @Param("state") Integer state);

}
