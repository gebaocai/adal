package me.baocai.adal.web.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author gebaocai
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("adal_goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String num;


}
