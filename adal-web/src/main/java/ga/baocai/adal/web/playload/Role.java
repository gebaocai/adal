package ga.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "角色对象", description = "角色对象")
public class Role {
    private String id;
    private String name;
    private String description;
}
