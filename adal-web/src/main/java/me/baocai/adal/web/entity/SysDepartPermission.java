package me.baocai.adal.web.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 部门权限表
 * </p>
 *
 * @author gebaocai
 * @since 2020-12-23
 */
@Data
@Builder
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

    @Tolerate
    SysDepartPermission() {

    }

    /**
     * 重写equals方法
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysDepartPermission departPermission = (SysDepartPermission) o;
        return Objects.equals(departId, departPermission.departId) &&
                Objects.equals(permissionId, departPermission.permissionId)&&
                Objects.equals(dataRuleIds, departPermission.dataRuleIds);
    }
}
