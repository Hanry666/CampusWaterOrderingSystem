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
@ApiModel(value="HomeProduct对象", description="")
public class HomeProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "展示ID")
      @TableId(value = "DISPLAY_ID", type = IdType.AUTO)
    private BigDecimal displayId;

    @ApiModelProperty(value = "分类ID(外键)")
    private BigDecimal categoryId;

    @ApiModelProperty(value = "商品名字")
    @TableField(exist = false, value = "PRODUCT_NAME")
    private String productName;

    @ApiModelProperty(value = "商品图片URL")
    @TableField(exist = false, value = "IMAGE_URL")
    private String imageUrl;

    @ApiModelProperty(value = "商品ID(外键)")
    private BigDecimal productId;



}
