package me.baocai.adal.web.mapper;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.AdalWebApplicationTests;
import me.baocai.adal.web.model.Goods;
import me.baocai.adal.web.service.GoodsService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * UserDao 测试
 * </p>
 */
@Slf4j
public class GoodsDaoTest extends AdalWebApplicationTests {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void testSelect() {
        List<Goods> goodsList = goodsService.list();
        Assert.assertEquals(true, goodsList.size() > 0);
        log.info("【goodsList】= {}", goodsList);
    }
}