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
@ApiModel(value="Delivery对象", description="")
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "配送员ID")
      @TableId(value = "DELIVERY_ID", type = IdType.AUTO)
    private BigDecimal deliveryId;

    @ApiModelProperty(value = "配送员名字")
    private String deliveryName;

    @ApiModelProperty(value = "配送员手机号")
    private String deliveryPhone;

    @ApiModelProperty(value = "配送员密码")
    private String deliveryPassword;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "地址ID(外键)")
    private BigDecimal addressId;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "用户角色(配送员)")
    private String role;

    @ApiModelProperty(value = "配送员状态")
    private BigDecimal isDelete;

    @ApiModelProperty(value = "配送员TOKEN")
    private String token;
}
