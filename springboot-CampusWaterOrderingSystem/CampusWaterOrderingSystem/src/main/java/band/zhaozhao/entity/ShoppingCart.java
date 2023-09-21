package band.zhaozhao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="ShoppingCart对象", description="")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车ID")
      @TableId(value = "CART_ID", type = IdType.AUTO)
    private BigDecimal cartId;

    @ApiModelProperty(value = "用户ID")
    private BigDecimal userId;

    @ApiModelProperty(value = "总计")
    private BigDecimal totalAmount;


}
