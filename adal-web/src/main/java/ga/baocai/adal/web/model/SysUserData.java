package ga.baocai.adal.web.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysUserData {
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

    private String phone;


    private Integer status;

    private Integer deleted;


    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private List<PermissionData.MenuData> menuData;
}
