package band.zhaozhao.service;

import band.zhaozhao.entity.ShoppingCart;
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
public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * 添加商品信息
     * @param userId 用户ID
     * @param productId 商品ID
     * @param productQuantity 商品数量
     * @return 信息
     */
    Result addProducts(BigDecimal userId, BigDecimal productId, BigDecimal productQuantity);


}
