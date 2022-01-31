package ga.baocai.adal.web.controller;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import ga.baocai.adal.web.common.CommonResponse;
import ga.baocai.adal.web.common.PageResult;
import ga.baocai.adal.web.common.Status;
import ga.baocai.adal.web.exception.SecurityException;
import ga.baocai.adal.web.playload.PageCondition;
import ga.baocai.adal.web.service.impl.MonitorService;
import ga.baocai.adal.web.util.PageUtil;
import ga.baocai.adal.web.util.SecurityUtil;
import ga.baocai.adal.web.vo.OnlineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 监控 Controller，在线用户，手动踢出用户等功能
 * </p>
 */
@Slf4j
@RestController
@RequestMapping("/api/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 在线用户列表
     *
     * @param pageCondition 分页参数
     */
//    @GetMapping("/online/user")
//    public CommonResponse onlineUser(PageCondition pageCondition) {
//        PageUtil.checkPageCondition(pageCondition, PageCondition.class);
//        PageResult<OnlineUser> pageResult = monitorService.onlineUser(pageCondition);
//        return CommonResponse.ofSuccess(pageResult);
//    }
//
//    /**
//     * 批量踢出在线用户
//     *
//     * @param names 用户名列表
//     */
//    @DeleteMapping("/online/user/kickout")
//    public CommonResponse kickoutOnlineUser(@RequestBody List<String> names) {
//        if (CollUtil.isEmpty(names)) {
//            throw new SecurityException(Status.PARAM_NOT_NULL);
//        }
//        if (names.contains(SecurityUtil.getCurrentUsername())) {
//            throw new SecurityException(Status.KICKOUT_SELF);
//        }
//        monitorService.kickout(names);
//        return CommonResponse.ofSuccess();
//    }
}
