package band.zhaozhao.mapper;

import band.zhaozhao.entity.OrderProduct;
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
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    /**
     * 添加上订单商品
     * @param orderId 订单ID
     * @param productId 商品ID
     * @param productQuantity 商品数量
     * @param orderPrice 订单价格
     * @param addressId 订单地址
     */
    void addOrderProduct(BigDecimal orderId, BigDecimal productId, BigDecimal productQuantity, BigDecimal orderPrice,BigDecimal addressId);

    /**
     * 按订单ID查看订单信息
     * @param orderId 订单ID
     * @return 订单信息
     */
    List<OrderProduct> selectByOrderId(BigDecimal orderId);

    /**
     * 查看所有订单
     * @return 订单信息
     */
    List<OrderProduct> selectAllOrders();

    /**
     * 为订单添加地址
     * @param addressId 地址Id
     * @param orderId 订单ID
     * @return 信息
     */
    Integer addAddressIdByOrderId(BigDecimal addressId, BigDecimal orderId);

    /**
     * 按订单Id查看订单价格
     * @param orderId 订单ID
     * @return 价格
     */
    BigDecimal selectOrderPriceByOrderId(BigDecimal orderId);
}
