package band.zhaozhao.mapper;

import band.zhaozhao.entity.Product;
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
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 按商品ID获取商品详情
     * @param productId 商品ID
     * @return 商品详情
     */
    List<Product> getProductDetailsById(Integer productId);

    /**
     * 按商品ID查看价格
     * @param productId 商品ID
     * @return 价格
     */
    Integer selectPriceById(BigDecimal productId);

    /**
     * 通过商品Id查看商品价格
     * @param productId 商品ID
     * @return 价格
     */
    BigDecimal selectProductPriceByProductId(BigDecimal productId);

    /**
     * 按照商品Id查看商品库存
     * @param productId 商品ID
     * @return 商品库存
     */
    BigDecimal selectProductStockByProduct(BigDecimal productId);

    /**
     * 更新商品库存
     * @param stock 库存
     * @param productId 商品Id
     * @return 信息
     */
    Integer upadteProductStock(BigDecimal stock ,BigDecimal productId);


    /**
     * 添加商品
     * @param categoryId 分类ID
     * @param imageId 图片ID
     * @param productName 商品名
     * @param productDescription 商品描述
     * @param productPrice 商品价格
     * @param productStock 订单库存
     * @param productStatus 订单状态
     * @param productDetails 商品详情
     * @return 信息
     */
    Integer addProdcuts(BigDecimal categoryId,BigDecimal imageId, String productName, String productDescription, BigDecimal productPrice, BigDecimal productStock, String productStatus, String productDetails);

    /**
     * 通过商品名查看商品信息
     * @param productName 商品名
     * @return 商品信息
     */
    List<Product> selectProductByProductName(String productName);

    /**
     * 删除商品
     * @param productId 商品Id
     * @return 信息
     */
    Integer deleteProductsByProductId(BigDecimal productId);

    /**
     * 修改商品信息
     * @param productId 商品Id
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
    Integer modProductsById(BigDecimal productId, BigDecimal categoryId, BigDecimal imageId, String productName, String productDescription, BigDecimal productPrice, BigDecimal productStock, String productStatus, String productDetails);

    /**
     * 分页查新商品信息
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @return 商品信息
     */
    List<Product> selectAllProducts(int pageSize, int pageNum);

    /**
     * 按名字搜索商品信息
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @param productName 商品名
     * @return 信息
     */
    List<Product> selectProductsByName(int pageSize, int pageNum, String productName);

    /**
     * 查看商品条数
     * @return 条数
     */
    int getAllProducts();

    /**
     * 按名字查看条数
     * @param productName 商品名
     * @return 条数
     */
    int selectAllProductByName(String productName);

    /**
     * 搜索ID按名字
     * @param productName 商品名
     * @return ID
     */
    Integer selectProductIdByProductName(String productName);
}
