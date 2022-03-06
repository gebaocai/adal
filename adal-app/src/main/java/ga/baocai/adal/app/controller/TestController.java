package ga.baocai.adal.app.controller;

import ga.baocai.adal.app.common.CommonResponse;
import ga.baocai.adal.app.common.Status;
import ga.baocai.adal.app.exception.JsonException;
import ga.baocai.adal.app.model.Goods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @GetMapping(value = "/list")
    public CommonResponse list() {
        log.info("测试列表查询");
        Goods goods = Goods.builder().name("apple").price("10.1").number("20").build();
        return CommonResponse.ofSuccess(goods);
    }

    @GetMapping(value = "/add")
    public CommonResponse add() {
        log.info("测试列表添加");
        return CommonResponse.ofMessage("测试列表添加");
    }

    @GetMapping("/{id}")
    public CommonResponse update(@PathVariable Long id) {
        log.info("测试列表修改");
        return CommonResponse.ofSuccess("测试列表修改");
    }

    @GetMapping("/exception")
    public CommonResponse exception() {
        log.info("异常处理");
        throw new JsonException(Status.ERROR);
    }
}
