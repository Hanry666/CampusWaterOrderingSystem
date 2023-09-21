package band.zhaozhao.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Zzhaozhao
 * @date 2023/6/7
 * @apiNote
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequest {

    @ApiModelProperty(value = "用户ID")
    private BigDecimal userId;

    @ApiModelProperty(value = "购物车ID")
    private BigDecimal cartId;

    @ApiModelProperty(value = "订单产品信息")
    private List<OrderProduct> items;

    @ApiModelProperty(value = "购物车信息")
    private List<ShoppingCart> item;

    @ApiModelProperty(value = "商品地址")
    private List<Address> shippingAddress;

}
