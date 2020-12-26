package me.baocai.adal.web.mapper;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.AdalWebApplicationTests;
import me.baocai.adal.web.entity.Goods;
import me.baocai.adal.web.enums.StateEnum;
import me.baocai.adal.web.service.GoodsService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Test
    public void getOne() throws JsonProcessingException {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "apple");
        Goods goods = goodsService.getOne(wrapper);
        Assert.assertNotNull(goods);
        ObjectMapper Obj = new ObjectMapper();
        log.info("【goods】= {}", Obj.writeValueAsString(goods));
    }

    @Test
    public void listMap() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "apple");
        List<Map<String, Object>> list = goodsService.listMaps(wrapper);
        Assert.assertEquals(true, list.size() > 0);
        list.stream().forEach(X -> {
            Set keys = X.keySet();
            log.info("【goodsMap keys】= {}", keys);
        });
        log.info("【goodsMap】= {}", list);
    }

    @Test
    public void save() {
        Goods goods = Goods.builder().name("苹果").num("10").state(StateEnum.ACTIVATED).build();
        boolean result = goodsService.addGoods(goods);
        Assert.assertEquals(true, result);
        log.info("【save goods】= {}", goods);
    }

    @Test
    public void batchSave() {
        ArrayList<Goods> goodsArrayList = CollUtil.newArrayList();
        for (int i = 0; i <= 10000; i++) {
            Goods goods = Goods.builder().name("apple" + i).num("10").state(StateEnum.ACTIVATED).build();
            goodsArrayList.add(goods);
        }

        boolean result = goodsService.saveBatch(goodsArrayList, 200);
        Assert.assertEquals(true, result);
        log.info("【save goods size】= {}", goodsArrayList.size());
    }

    @Test
    public void batchUpdate() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("num", "10");
        Goods goods = Goods.builder().num("100").build();
        boolean result = goodsService.update(goods, wrapper);
        Assert.assertEquals(true, result);
        log.info("【update goods】= {}", goods);
    }

    @Test
    public void page() {
        IPage<Goods> goodsIPage = goodsService.selectUserPage(new Page(), 0);
        Assert.assertEquals(10L, goodsIPage.getSize());
        log.info("【page goods】= {}", goodsIPage);
    }

    @Test
    public void delete() {
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.eq("num", "100");
        boolean result = goodsService.remove(wrapper);
        Assert.assertEquals(true, result);
    }
}