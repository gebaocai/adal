package me.baocai.adal.web.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 部门权限表
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysDepartPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 部门id
     */
    private String departId;

    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 数据规则id
     */
    private String dataRuleIds;


}
