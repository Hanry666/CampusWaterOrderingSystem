package band.zhaozhao.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
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
@ApiModel(value="Users对象", description="")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    @TableId(value = "USER_ID", type = IdType.AUTO)
    private BigDecimal userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "用户电话")
    private String userPhone;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "地址ID(外键)")
    private BigDecimal addressId;

    @ApiModelProperty(value = "账户余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "角色(个人用户、单位用户)")
    private String role;

    @ApiModelProperty(value = "用户优先级")
    private BigDecimal priority;

    @ApiModelProperty(value = "用户状态")
    private BigDecimal isDelete;

    @ApiModelProperty(value = "用户TOKEN")
    private String token;

}
