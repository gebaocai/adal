package me.baocai.adal.web.controller;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.RabbitConsts;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.exception.JsonException;
import me.baocai.adal.web.message.MessageStruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    @GetMapping("/mq")
    public CommonResponse send() {
        log.info("发送消息");
        for (int i = 0; i < 10000; i++) {
//            ThreadUtil.sleep(1, TimeUnit.MILLISECONDS);
            rabbitTemplate.convertAndSend(RabbitConsts.DIRECT_MODE_QUEUE_ONE, new MessageStruct("direct message " + i));
        }
        return CommonResponse.ofSuccess("发送消息");
    }
}
