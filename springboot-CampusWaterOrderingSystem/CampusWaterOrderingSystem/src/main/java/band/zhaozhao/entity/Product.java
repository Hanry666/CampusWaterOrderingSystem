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
 * @since 2023-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="Product对象", description="")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品ID")
      @TableId(value = "PRODUCT_ID", type = IdType.AUTO)
    private BigDecimal productId;

    @ApiModelProperty(value = "种类ID(外键)")
    private BigDecimal categoryId;

    @ApiModelProperty(value = "商品图片ID(外键)")
    private BigDecimal imageId;

    @ApiModelProperty(value = "商品名")
    private String productName;

    @ApiModelProperty(value = "商品描述")
    private String productDescription;

    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品库存")
    private BigDecimal productStock;

    @ApiModelProperty(value = "商品状态")
    private String productStatus;

    @ApiModelProperty(value = "商品详情")
    private String productDetails;

    @ApiModelProperty(value = "商品状态")
    private BigDecimal isDelete;

}
