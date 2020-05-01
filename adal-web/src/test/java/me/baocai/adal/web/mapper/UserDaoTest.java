package me.baocai.adal.web.mapper;

import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.AdalWebApplicationTests;
import me.baocai.adal.web.model.User;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * UserDao 测试
 * </p>
 */
@Slf4j
public class UserDaoTest extends AdalWebApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void findByUsernameIn() {
        List<String> usernameList = Lists.newArrayList("admin", "user");
        List<User> userList = userDao.findByUsernameIn(usernameList);
        Assert.assertEquals(2, userList.size());
        log.info("【userList】= {}", userList);
    }

    @Test
    public void findByUsernameOrEmailOrPhone() {
        Optional<User> user = userDao.findByUsernameOrEmailOrPhone("admin", null, null);
        Assert.assertEquals(true, user.isPresent());
        log.info("【user】= {}", user.get());
    }
}