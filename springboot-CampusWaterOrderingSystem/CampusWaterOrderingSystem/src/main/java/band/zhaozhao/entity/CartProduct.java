package band.zhaozhao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="CartProduct对象", description="")
public class CartProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车ID")
      @TableId(value = "CART_ID", type = IdType.AUTO)
    private BigDecimal cartId;

    @ApiModelProperty(value = "商品ID")
    private BigDecimal productId;

    @ApiModelProperty(value = "商品数量")
    private Long productQuantity;

    @ApiModelProperty(value = "商品总价格")
    private Long cartPrice;

    @ApiModelProperty(value = "商品名")
    @TableField(exist = false, value = "PRODUCT_NAME")
    private String productName;


}
