package me.baocai.adal.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author gebaocai
 * @since 2022-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermissionApi implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 用户主键
     */
    private String permissionId;

    /**
     * 角色主键
     */
    private String apiId;


}
