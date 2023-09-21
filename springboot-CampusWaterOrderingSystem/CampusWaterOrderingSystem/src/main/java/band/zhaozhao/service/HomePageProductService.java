package band.zhaozhao.service;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.HomePageProduct;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
public interface HomePageProductService extends IService<HomePageProduct> {
    /**
     * 展示轮播商品的服务接口
     * @return 信息
     */
    Result selectAllProduct();

    /**
     * 添加轮播商品
     * @param productId 商品
     * @param imageUrl 图片地址
     * @return 信息
     */
    Result addHomePageProduct(BigDecimal productId, String imageUrl);

    /**
     * 按轮播商品ID删除
     * @param carouselId 轮播ID
     * @return 信息
     */
    Result deleteHomePageProduct(BigDecimal carouselId);

    /**
     * 修改轮播商品的信息
     * @param carouselId 轮播ID
     * @param productId 商品ID
     * @param imageUrl 商品地址
     * @return 信息
     */
    Result modHomePageProduct(BigDecimal carouselId, BigDecimal productId, String imageUrl);

    /**
     * 按分页信息得到所有轮播商品
     * @param query 分页信息
     * @return 信息
     */
    Result getAllHomePageProduct(QueryPageParam query);
}
