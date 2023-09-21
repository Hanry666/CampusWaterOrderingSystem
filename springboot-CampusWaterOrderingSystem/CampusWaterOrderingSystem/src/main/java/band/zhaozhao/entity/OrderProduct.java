package band.zhaozhao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

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
@ApiModel(value="OrderProduct对象", description="")
public class OrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID(外键)")
      @TableId(value = "ORDER_ID", type = IdType.AUTO)
    private BigDecimal orderId;

    @ApiModelProperty(value = "商品ID")
    private BigDecimal productId;

    @ApiModelProperty(value = "商品数量")
    private BigDecimal productQuantity;

    @TableField(exist = false, value = "ORDER_DATE")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "订单时间")
    private LocalDateTime orderDate;

    @ApiModelProperty(value = "本单金额")
    private BigDecimal orderPrice;

    @TableField(exist = false, value = "PRODUCT_NAME")
    @ApiModelProperty(value = "商品名字")
    private String productName;

    @TableField(exist = false, value = "TOTAL_AMOUNT")
    @ApiModelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    @TableField(exist = false, value = "PRODUCT_PRICE")
    @ApiModelProperty(value = "商品价格")
    private BigDecimal productPrice;

    @TableField(exist = false, value = "ADDRESS_ID")
    @ApiModelProperty(value = "地址ID")
    private BigDecimal addressId;

    @TableField(exist = false, value = "ORDER_NUMBER")
    @ApiModelProperty(value = "订单号")
    private BigDecimal orderNumber;

    @TableField(exist = false, value = "ORDER_STATUS")
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "校区")
    @TableField(exist = false, value = "CAMPUS")
    private String campus;

    @ApiModelProperty(value = "楼号")
    @TableField(exist = false, value = "BUILDING")
    private String building;

    @ApiModelProperty(value = "门牌号")
    @TableField(exist = false, value = "HOUSE_NUMBER")
    private String houseNumber;

    @TableField(exist = false, value = "USER_NAME")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @TableField(exist = false, value = "USER_PHONE")
    @ApiModelProperty(value = "用户手机号")
    private String userPhone;

}
