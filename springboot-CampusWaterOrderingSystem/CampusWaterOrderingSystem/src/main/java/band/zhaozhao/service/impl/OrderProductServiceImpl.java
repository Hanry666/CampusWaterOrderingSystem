package band.zhaozhao.service.impl;

import band.zhaozhao.entity.OrderProduct;
import band.zhaozhao.mapper.OrderProductMapper;
import band.zhaozhao.service.OrderProductService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@Service
public class OrderProductServiceImpl extends ServiceImpl<OrderProductMapper, OrderProduct> implements OrderProductService {

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Override
    public Result addAddressByOrderId(BigDecimal addressId, BigDecimal orderId) {
        //添加地址给相应的订单号
        Integer integer = orderProductMapper.addAddressIdByOrderId(addressId, orderId);
        if (integer == null) {
            return Result.error("添加商品失败",201,null);
        }
        return Result.success("添加地址成功",200,null);
    }
}
