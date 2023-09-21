package band.zhaozhao.service;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.Product;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
public interface ProductService extends IService<Product> {

    /**
     * 获取商品详情接口
     * @param productId 商品ID
     * @return
     */
    Result getProductDetailsById(Integer productId);

    /**
     * 添加商品接口
     * @param categoryId 分类ID
     * @param imageId 图片ID
     * @param productName 商品名
     * @param productDescription 商品描述
     * @param productPrice 商品价格
     * @param productStock 商品库存
     * @param productStatus 商品状态
     * @param productDetails 商品详情
     * @return 信息
     */
    Result addProducts(BigDecimal categoryId,BigDecimal imageId, String productName, String productDescription, BigDecimal productPrice, BigDecimal productStock, String productStatus, String productDetails);

    /**
     * 按商品ID删除商品
     * @param productId 商品ID
     * @return 信息
     */
    Result deleteProductsByProductId(BigDecimal productId);

    /**
     * 修改商品接口
     * @param productId 商品ID
     * @param categoryId 分类ID
     * @param imageId 图片ID
     * @param productName 商品名
     * @param productDescription 商品详情
     * @param productPrice 商品价格
     * @param productStock 商品库存
     * @param productStatus 商品状态
     * @param productDetails 商品详情
     * @return 信息
     */
    Result modProducts(BigDecimal productId, BigDecimal categoryId, BigDecimal imageId, String productName, String productDescription, BigDecimal productPrice, BigDecimal productStock, String productStatus, String productDetails);

    /**
     * 获取所有的商品接口
     * @param query 分页信息
     * @return 信息
     */
    Result getAllProducts(QueryPageParam query);

    /**
     * 获取所有的名字商品
     * @param query 分页信息
     * @return 信息
     */
    Result selectProductByProductName(QueryPageParam query);
}
