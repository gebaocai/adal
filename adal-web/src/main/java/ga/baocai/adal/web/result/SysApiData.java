package ga.baocai.adal.web.result;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ga.baocai.adal.web.entity.SysApi;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysApiData {
    /**
     * ID
     */
    private String id;
    private String key;
    /**
     * 父id
     */
    private String parentId;
    private String name;
    /**
     * 机构/部门名称
     */
    private String title;

    private String url;

    /**
     * 描述
     */
    private String description;

    /**
     * 菜单排序
     */
    private Double sortNo;
    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    private Integer menuType;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    private List<SysApiData> children;

    public SysApiData(SysApi api) {
        this.id = api.getId();
        this.key = api.getId();
        this.title = api.getName();
        this.name = api.getName();
        this.createTime = api.getCreateTime();
        this.updateTime = api.getUpdateTime();
        this.url = api.getUrl();
        this.description = api.getDescription();
        this.sortNo = api.getSortNo();
        this.menuType = api.getMenuType();
        this.parentId = api.getParentId();
    }
}
