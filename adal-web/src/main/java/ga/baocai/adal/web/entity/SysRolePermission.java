package ga.baocai.adal.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 角色权限表
 * </p>
 */

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = -8393506242587909934L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 数据权限
     */
    private String dataRuleIds;

    /**
     * 操作时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateDate;

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
        SysRolePermission roldPermission = (SysRolePermission) o;
        return Objects.equals(roleId, roldPermission.roleId) &&
                Objects.equals(permissionId, roldPermission.permissionId);
    }

    @Tolerate
    SysRolePermission() {

    }
}
