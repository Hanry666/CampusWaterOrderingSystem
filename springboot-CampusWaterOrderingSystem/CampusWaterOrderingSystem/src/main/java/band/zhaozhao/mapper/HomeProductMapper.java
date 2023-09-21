package band.zhaozhao.mapper;

import band.zhaozhao.entity.HomeProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@Mapper
public interface HomeProductMapper extends BaseMapper<HomeProduct> {

    /**
     * 通过分类ID查看商品
     * @param categoryId 分类ID
     * @return 商品信息
     */
    List<HomeProduct> getProductCategories(Integer categoryId);

    /**
     * 添加到展示的商品
     * @param categoryId 分类ID
     * @param productId 商品ID
     * @return 信息
     */
    Integer addDisplayById(BigDecimal categoryId, Integer productId);
}
