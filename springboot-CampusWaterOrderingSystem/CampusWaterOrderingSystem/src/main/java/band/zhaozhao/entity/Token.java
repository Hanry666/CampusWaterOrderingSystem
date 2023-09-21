package band.zhaozhao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



/**
 * @author Zzhaozhao
 * @date 2023/6/18
 * @apiNote
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Token {

    @ApiModelProperty(value = "ID信息")
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "角色")
    private String role;
}
