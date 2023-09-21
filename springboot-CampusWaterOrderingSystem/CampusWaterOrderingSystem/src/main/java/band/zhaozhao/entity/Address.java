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
@ApiModel(value="Address对象", description="")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "地址ID")
    @TableId(value = "ADDRESS_ID", type = IdType.AUTO)
    private BigDecimal addressId;

    @ApiModelProperty(value = "校区")
    private String campus;

    @ApiModelProperty(value = "楼号")
    private String building;

    @ApiModelProperty(value = "门牌号")
    private String houseNumber;

    @ApiModelProperty(value = "用户Id")
    private BigDecimal userId;

    @ApiModelProperty(value = "状态")
    private BigDecimal isDelete;



}
