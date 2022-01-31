package ga.baocai.adal.web.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import ga.baocai.adal.web.playload.PageCondition;
import lombok.extern.slf4j.Slf4j;
import ga.baocai.adal.web.common.Consts;
import ga.baocai.adal.web.common.PageResult;
import ga.baocai.adal.web.entity.SysUser;
import ga.baocai.adal.web.mapper.SysUserDao;
import ga.baocai.adal.web.util.RedisUtil;
import ga.baocai.adal.web.util.SecurityUtil;
import ga.baocai.adal.web.vo.OnlineUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 监控 Service
 * </p>
 *
 * @package: com.xkcoding.rbac.security.service
 * @description: 监控 Service
 * @author: yangkai.shen
 * @date: Created in 2018-12-12 00:55
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
@Service
public class MonitorService {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 在线用户分页列表
     *
     * @param pageCondition 分页参数
     * @return 在线用户分页列表
     */
    public PageResult<OnlineUser> onlineUser(PageCondition pageCondition) {
        PageResult<String> keys = redisUtil.findKeysForPage(Consts.REDIS_JWT_KEY_PREFIX + Consts.SYMBOL_STAR, pageCondition.getCurrentPage(), pageCondition.getPageSize());
        List<String> rows = keys.getRows();
        Long total = keys.getTotal();

        // 根据 redis 中键获取用户名列表
        List<String> usernameList = rows.stream()
                .map(s -> StrUtil.subAfter(s, Consts.REDIS_JWT_KEY_PREFIX, true))
                .collect(Collectors.toList());
        // 根据用户名查询用户信息
        List<SysUser> userList = sysUserDao.findByUsernameIn(usernameList);

        // 封装在线用户信息
        List<OnlineUser> onlineUserList = CollUtil.newArrayList();
        userList.forEach(user -> onlineUserList.add(OnlineUser.create(user)));

        return new PageResult<>(onlineUserList, total);
    }

    /**
     * 踢出在线用户
     *
     * @param names 用户名列表
     */
    public void kickout(List<String> names) {
        // 清除 Redis 中的 JWT 信息
        List<String> redisKeys = names.parallelStream()
                .map(s -> Consts.REDIS_JWT_KEY_PREFIX + s)
                .collect(Collectors.toList());
        redisUtil.delete(redisKeys);

        // 获取当前用户名
        String currentUsername = SecurityUtil.getCurrentUsername();
        names.parallelStream()
                .forEach(name -> {
                    // TODO: 通知被踢出的用户已被当前登录用户踢出，
                    //  后期考虑使用 websocket 实现，具体伪代码实现如下。
                    //  String message = "您已被用户【" + currentUsername + "】手动下线！";
                    log.debug("用户【{}】被用户【{}】手动下线！", name, currentUsername);
                });
    }
}
