package band.zhaozhao.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author Zzhaozhao
 * @date 2023/6/22
 * @apiNote
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Page {

    @ApiModelProperty(value = "总分页条数")
    double totalRecords;

    @ApiModelProperty(value = "总分页数")
    int totalPages;

    @ApiModelProperty(value = "分类信息")
    List<Category> categoryList;

    @ApiModelProperty(value = "用户信息")
    List<Users> usersList;

    @ApiModelProperty(value = "配送员信息")
    List<Delivery> deliveryList;

    @ApiModelProperty(value = "商品信息")
    List<Product> productList;

}
