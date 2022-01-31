package ga.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "部门对象", description = "部门对象")
public class DepartPermission {
    /**
     * 部门id
     */
    private String departId;

    /**
     * 权限id组
     */
    private String permissionIds;
}
