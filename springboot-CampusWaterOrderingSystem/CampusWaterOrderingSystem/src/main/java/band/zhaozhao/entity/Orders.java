package band.zhaozhao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="Orders对象", description="")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID")
      @TableId(value = "ORDER_ID", type = IdType.AUTO)
    private BigDecimal orderId;

    @ApiModelProperty(value = "用户ID")
    private BigDecimal userId;

    @ApiModelProperty(value = "配送员ID")
    private BigDecimal deliveryId;

    @ApiModelProperty(value = "订单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderDate;

    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "订单号")
    private BigDecimal orderNumber;

    @TableField(exist = false, value = "DELIVERY_NAME")
    @ApiModelProperty(value = "配送员名字")
    private String deliveryName;

    @TableField(exist = false, value = "DELIVERY_PHONE")
    @ApiModelProperty(value = "配送员手机号")
    private String deliveryPhone;

    @TableField(exist = false, value = "USER_NAME")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @TableField(exist = false, value = "USER_PHONE")
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

    @ApiModelProperty(value = "订单详情信息")
    private List<OrderProduct> items;


}
