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
@ApiModel(value="Category对象", description="")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "种类ID")
      @TableId(value = "CATEGORY_ID", type = IdType.AUTO)
    private BigDecimal categoryId;

    @ApiModelProperty(value = "种类名")
    private String categoryName;

    @ApiModelProperty(value = "种类状态")
    private BigDecimal isDelete;
}
