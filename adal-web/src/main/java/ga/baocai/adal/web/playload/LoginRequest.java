package ga.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 登录请求参数
 * </p>
 */
@ApiModel(value = "登录对象", description = "登录对象")
@Data
public class LoginRequest {

    /**
     * 用户名或邮箱或手机号
     */
    @NotBlank(message = "用户名不能为空")
    private String usernameOrEmailOrPhone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = true;

    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "验证码key")
    private String checkKey;

}
