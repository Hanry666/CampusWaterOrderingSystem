package band.zhaozhao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Zzhaozhao
 * @date 2023/6/21
 * @apiNote
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Cart {

    @ApiModelProperty(value = "购物车商品信息")
    private List<CartProduct> cartProduct;

    @ApiModelProperty(value = "购物车总金额")
    private BigDecimal totalAmount;
}
