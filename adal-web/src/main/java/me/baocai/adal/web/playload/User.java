package me.baocai.adal.web.playload;
import lombok.Data;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User implements Serializable {

    private String id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private LocalDate birthday;

    /**
     * 性别（1：男 2：女）
     */
    private Integer gender;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    private Integer status;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    private String roleIds;
    private String departIds;
    private String departNames;
}
