package band.zhaozhao.mapper;

import band.zhaozhao.entity.CartProduct;
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
 * @since 2023-06-21
 */
@Mapper
public interface CartProductMapper extends BaseMapper<CartProduct> {

    /**
     * 购物车添加商品接口
     * @param cartId 购物车Id
     * @param productId 商品Id
     * @param productQuantity 商品数量
     * @param cartPrice 购物车金额
     * @return 信息
     */
    Integer addProducts(Integer cartId, BigDecimal productId, BigDecimal productQuantity, BigDecimal cartPrice);

    /**
     * 通过用户ID查看购物车信息
     * @param userId 用户Id
     * @return 购物车信息
     */
    List<CartProduct> selectAllByUserId(BigDecimal userId);

    /**
     * 删除商品
     * @param productId 商品Id
     * @return 信息
     */
    Integer updateProduct(BigDecimal productId);

    /**
     * 得到总价格接口
     * @param cartId 购物车Id
     * @return 总价
     */
    BigDecimal getSumPrice(Integer cartId);
}
