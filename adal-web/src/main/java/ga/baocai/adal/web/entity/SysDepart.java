package ga.baocai.adal.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class SysDepart implements Serializable {
    private static final long serialVersionUID = -8114729001599287664L;

    /**
     * ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 父机构ID
     */
    private String parentId;
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
     * 删除状态（0，正常，1已删除）
     */
    @TableLogic
    private Integer deleted;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新日期
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
