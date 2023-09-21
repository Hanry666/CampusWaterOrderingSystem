package band.zhaozhao.service;

import band.zhaozhao.entity.CartProduct;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-21
 */
public interface CartProductService extends IService<CartProduct> {

    /**
     * 删除商品
     * @param productId 商品ID
     * @return 信息
     */
    Result deleteProduct(BigDecimal productId);

    /**
     * 展示用户的所有购物车信息
     * @param userId 用户ID
     * @return 信息
     */
    Result showUserCartByUserId(BigDecimal userId);

    /**
     * 结算购物车
     * @param userId 商品ID
     * @param addressId 地址ID
     * @return 信息
     */
    Result checkOutCartProduct(BigDecimal userId, BigDecimal addressId);
}
