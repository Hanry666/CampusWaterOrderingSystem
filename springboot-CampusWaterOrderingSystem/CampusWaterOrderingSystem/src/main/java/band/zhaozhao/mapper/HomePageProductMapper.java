package band.zhaozhao.mapper;

import band.zhaozhao.entity.HomePageProduct;
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
public interface HomePageProductMapper extends BaseMapper<HomePageProduct> {

    /**
     * 查看所有的商品
     * @return 商品信息
     */
    List<HomePageProduct> selectAllProduct();

    /**
     * 添加商品
     * @param productId 商品ID
     * @param imageUrl 图片地址
     * @return 信息
     */
    Integer addHomePageProduct(BigDecimal productId, String imageUrl);

    /**
     * 通过商品ID查看商品信息
     * @param productId 商品ID
     * @return 商品信息
     */
    List<HomePageProduct> selectIdByProductId(BigDecimal productId);

    /**
     * 通过轮播Id删除
     * @param carouselId 轮播ID
     * @return 信息
     */
    Integer deleteHomePageProductByCarouselId(BigDecimal carouselId);

    /**
     * 修改商品信息
     * @param carouselId 轮播ID
     * @param productId 商品ID
     * @param imageUrl 图片地址
     * @return 信息
     */
    Integer modHomePageProduct(BigDecimal carouselId, BigDecimal productId, String imageUrl);

    /**
     * 按轮播查看商品信息
     * @param carouselId 轮播ID
     * @return 商品信息
     */
    List<HomePageProduct> selectIdByCarouseId(BigDecimal carouselId);

    /**
     * 分页查看商品信息
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @return 信息
     */
    List<HomePageProduct> selectAllHomePageProducts(int pageSize, int pageNum);
}
