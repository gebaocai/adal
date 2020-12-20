package me.baocai.adal.web.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import me.baocai.adal.web.enums.StateEnum;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author gebaocai
 * @since 2020-05-02
 */
@ApiModel("商品模型")
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("adal_goods")
@AllArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("商品id")
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("商品数量")
    private String num;

    /**
     * 状态枚举（带{@link me.baocai.adal.web.enums.StateEnum})
     */
    private StateEnum state;

    @TableLogic
    private Integer deleted;
}
