package ga.baocai.adal.web.controller;


import cn.hutool.core.util.PageUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.entity.Goods;
import ga.baocai.adal.web.enums.StateEnum;
import ga.baocai.adal.web.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gebaocai
 * @since 2020-05-02
 */
@Api("商品接口")
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("商品列表")
    @GetMapping(value = "/list")
    public CommonResponse list(Integer pageNum) {
        log.debug("goods list");
        Page p = new Page();
        p.setCurrent(PageUtil.getStart(pageNum, 20));
        p.setSize(20L);
        IPage<Goods> goodsIPage = goodsService.page(p);
        return CommonResponse.ofStatus(Status.SUCCESS, goodsIPage);
    }

    @ApiOperation("增加商品")
    @PostMapping(value = "/add")
    public CommonResponse add(@RequestBody Goods goods) {
        log.debug("add goods");
        goods.setState(StateEnum.ACTIVATED);
        goodsService.addGoods(goods);
        return CommonResponse.ofStatus(Status.SUCCESS, goods);
    }

}

