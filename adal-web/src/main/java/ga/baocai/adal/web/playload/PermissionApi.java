package ga.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "权限接口", description = "权限接口")
public class PermissionApi {
    /**
     * apiIds
     */
    private String apiIds;

    /**
     * 权限id
     */
    private String permissionId;
}
