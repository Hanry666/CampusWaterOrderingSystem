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
@ApiModel(value="Image对象", description="")
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "图片ID")
      @TableId(value = "IMAGE_ID", type = IdType.AUTO)
    private BigDecimal imageId;

    @ApiModelProperty(value = "图片URL")
    private String imageUrl;


}
