package me.baocai.adal.web.controller;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.exception.JsonException;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {
    @GetMapping(value = "/list")
    public CommonResponse list() {
        log.info("测试列表查询");
        return CommonResponse.ofMessage("测试列表查询");
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
