package me.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "接口对象", description = "接口对象")
public class Api {
    private String id;
    private String name;
    private String url;
    private String description;
}
