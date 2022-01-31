package ga.baocai.adal.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDepart implements Serializable {
    private static final long serialVersionUID = 1373501597048787788L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 部门id
     */
    private String departId;
}
