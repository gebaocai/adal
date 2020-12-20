package me.baocai.adal.web.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import me.baocai.adal.web.common.CommonResponse;
import me.baocai.adal.web.common.Consts;
import me.baocai.adal.web.common.Status;
import me.baocai.adal.web.exception.SecurityException;
import me.baocai.adal.web.playload.LoginRequest;
import me.baocai.adal.web.util.JwtUtil;
import me.baocai.adal.web.util.RedisUtil;
import me.baocai.adal.web.vo.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 认证 Controller，包括用户注册，用户登录请求
 * </p>
 */
@Api(tags = "用户授权接口")
@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     */
    @ApiOperation("登录接口")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = JwtResponse.class),
            @ApiResponse(code = 401, message = "请先登录！")
    })
    @PostMapping("/login")
    public CommonResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String captcha = loginRequest.getCaptcha();
        if (captcha == null) {
            return CommonResponse.ofStatus(Status.CAPTCHA_ERROR);
        }
        String lowerCaseCode = captcha.toLowerCase();
        final String realKey = Consts.REDIS_CAPTCHA_PREFIX + Consts.SYMBOL_STAR +
                lowerCaseCode + Consts.SYMBOL_STAR + loginRequest.getCheckKey();
        Object checkCode = stringRedisTemplate.opsForValue().get(realKey);
        //当进入登录页时，有一定几率出现验证码错误 #1714
        if (checkCode == null || !checkCode.toString().equals(lowerCaseCode)) {
            return CommonResponse.ofStatus(Status.CAPTCHA_ERROR);
        }

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        return CommonResponse.ofSuccess(new JwtResponse(jwt));
    }

    @ApiOperation("登出接口")
    @PostMapping("/logout")
    public CommonResponse logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(Status.UNAUTHORIZED);
        }
        return CommonResponse.ofStatus(Status.LOGOUT);
    }

    /**
     * 后台生成图形验证码 ：有效
     *
     * @param key
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/randomImage/{key}")
    public void randomImage(HttpServletResponse response, @PathVariable String key) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4, 10);
        try {
            String code = lineCaptcha.getCode();
            String lowerCaseCode = code.toLowerCase();
            final String realKey = Consts.REDIS_CAPTCHA_PREFIX + Consts.SYMBOL_STAR +
                    lowerCaseCode + Consts.SYMBOL_STAR + key;
            stringRedisTemplate.opsForValue().set(realKey, lowerCaseCode, Consts.CAPTCHA_EXPIRATION, TimeUnit.SECONDS);
            response.setContentType("image/png");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            lineCaptcha.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
