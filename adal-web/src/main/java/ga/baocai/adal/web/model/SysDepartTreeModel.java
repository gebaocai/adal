package ga.baocai.adal.web.model;

import ga.baocai.adal.web.entity.SysDepart;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author gebaocai
 * @date 2020/12/25 0:27
 */
@Data
public class SysDepartTreeModel {
    /**
     * ID
     */
    private String id;
    private String key;
    /**
     * 父机构ID
     */
    private String parentId;
    /**
     * 机构/部门名称
     */
    private String title;
    /**
     * 机构/部门名称
     */
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
    /**
     * 状态（1启用，0不启用）
     */
    private String status;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新日期
     */
    private Date updateTime;

    /**
     * 子节点
     */
    private List<SysDepartTreeModel> children;

    /**
     * 将SysDepart对象转换成SysDepartTreeModel对象
     *
     * @param sysDepart
     */
    public SysDepartTreeModel(SysDepart sysDepart) {
        this.id = sysDepart.getId();
        this.key = sysDepart.getId();
        this.parentId = sysDepart.getParentId();
        this.title = sysDepart.getDepartName();
        this.departName = sysDepart.getDepartName();
        this.departNameEn = sysDepart.getDepartNameEn();
        this.departNameAbbr = sysDepart.getDepartNameAbbr();
        this.departOrder = sysDepart.getDepartOrder();
        this.description = sysDepart.getDescription();
        this.orgCategory = sysDepart.getOrgCategory();
        this.orgType = sysDepart.getOrgType();
        this.orgCode = sysDepart.getOrgCode();
        this.mobile = sysDepart.getMobile();
        this.fax = sysDepart.getFax();
        this.address = sysDepart.getAddress();
        this.memo = sysDepart.getMemo();
        this.status = sysDepart.getStatus();
        this.createBy = sysDepart.getCreateBy();
        this.createTime = sysDepart.getCreateTime();
        this.updateBy = sysDepart.getUpdateBy();
        this.updateTime = sysDepart.getUpdateTime();
    }
}
