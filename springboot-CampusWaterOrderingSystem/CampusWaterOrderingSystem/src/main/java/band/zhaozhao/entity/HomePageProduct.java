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
 * @since 2023-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value="HomePageProduct对象", description="")
public class HomePageProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品轮播ID")
    @TableId(value = "CAROUSEL_ID", type = IdType.AUTO)
    private BigDecimal carouselId;

    @ApiModelProperty(value = "种类名")
    @TableField(exist = false, value = "CATEGORY_NAME")
    private String categoryName;

    @ApiModelProperty(value = "商品名字")
    @TableField(exist = false, value = "PRODUCT_NAME")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    @TableField(exist = false, value = "PRODUCT_PRICE")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品ID")
    private BigDecimal productId;

    @ApiModelProperty(value = "商品图片URL")
    private String imageUrl;

    @ApiModelProperty(value = "首页商品状态")
    private BigDecimal isDelete;


}
