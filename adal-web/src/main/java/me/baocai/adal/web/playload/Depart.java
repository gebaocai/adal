package me.baocai.adal.web.playload;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "部门对象", description = "部门对象")
public class Depart {
    private String id;
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 机构/部门名称
     */
    @NotBlank(message = "机构/部门名称不能为空")
    private String departName;
    /**
     * 英文名
     */
    private String departNameEn;
    /**
     * 缩写
     */
    private String departNameAbbr;
    /**
     * 排序
     */
    private Integer departOrder;
    /**
     * 描述
     */
    private String description;
    /**
     * 机构类别 1组织机构，2岗位
     */
    private String orgCategory;
    /**
     * 机构类型
     */
    private String orgType;
    /**
     * 机构编码
     */
    private String orgCode;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 传真
     */
    private String fax;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String memo;
}
