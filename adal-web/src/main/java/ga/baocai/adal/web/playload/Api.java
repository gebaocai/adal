package ga.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "接口对象", description = "接口对象")
public class Api {
    private String id;
    /**
     * 父id
     */
    private String parentId;
    private String name;
    private String url;
    private String description;
    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    private Integer menuType;
}
