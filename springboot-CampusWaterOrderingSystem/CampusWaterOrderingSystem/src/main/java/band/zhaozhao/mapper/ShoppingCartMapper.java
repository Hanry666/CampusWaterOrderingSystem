package band.zhaozhao.mapper;

import band.zhaozhao.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-21
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

    /**
     * 添加商品
     * @param userId 用户ID
     * @return 信息
     */
    Integer addCart(BigDecimal userId);

    /**
     * 按用户ID搜索
     * @param userId 用户ID
     * @return 信息
     */
    Integer selectByUserId(BigDecimal userId);

    /**
     * 添加购物车信息
     * @param cartId 购物车Id
     * @param totalAmount 总价格
     * @return 信息
     */
    Integer addByCartId(Integer cartId, BigDecimal totalAmount);
}
