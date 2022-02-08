package ga.baocai.adal.web.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统用户数据范围表
 * </p>
 *
 * @author gebaocai
 * @since 2022-02-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserDataScope implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 机构id
     */
    private Long orgId;


}
