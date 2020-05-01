package me.baocai.adal.web.model;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -6202142152889273136L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;
}
