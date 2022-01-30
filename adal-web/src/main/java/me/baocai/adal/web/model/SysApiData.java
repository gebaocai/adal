package me.baocai.adal.web.model;

import lombok.Data;
import me.baocai.adal.web.entity.SysApi;

@Data
public class SysApiData {
    /**
     * ID
     */
    private String id;
    private String key;
    /**
     * 机构/部门名称
     */
    private String title;

    public SysApiData(SysApi api) {
        this.id = api.getId();
        this.key = api.getId();
        this.title = api.getName();
    }
}
