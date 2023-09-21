package band.zhaozhao.service;

import band.zhaozhao.entity.OrderProduct;
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
public interface OrderProductService extends IService<OrderProduct> {

    /**
     * 添加订单地址
     * @param addressId 地址信息
     * @param orderId 订单ID
     * @return 信息
     */
    Result addAddressByOrderId(BigDecimal addressId, BigDecimal orderId);
}
