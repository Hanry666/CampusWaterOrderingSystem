package band.zhaozhao.service;

import band.zhaozhao.entity.Orders;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 添加订单
     * @param userId 用户ID
     * @param productId 商品ID
     * @param orderDate 订单日期
     * @param productQuantity 商品数量
     * @param addressId 地址ID
     * @return 信息
     */
    Result addOrders(BigDecimal userId, BigDecimal productId, LocalDateTime orderDate, BigDecimal productQuantity, BigDecimal addressId);

    /**
     * 获取用正在进行的订单
     * @param userId 用户ID
     * @return 信息
     */
    Result getUserActiveOrder(BigDecimal userId);

    /**
     * 获取用户历史订单
     * @param userId 用户ID
     * @return 信息
     */
    Result getUserHistoryOrder(BigDecimal userId);

    /**
     * 更新订单状态
     * @param orderId 订单ID
     * @return 信息
     */
    Result updateOrderStatusByOrderId(BigDecimal orderId);

    /**
     * 未收到货接口
     * @param orderId 订单ID
     * @return 信息
     */
    Result updateOrderNotReceivedByOrderId(BigDecimal orderId);

    /**
     * 获取所有可获得的订单
     * @return 信息
     */
    Result getAllAvailableOrders();

    /**
     * 抢单接口
     * @param deliveryId  配送员ID
     * @param orderNumber 订单号
     * @return 信息
     */
    Result claimOrders(BigDecimal deliveryId, BigDecimal orderNumber);

    /**
     * 获取配送员正在进行的接口
     * @param deliveryId 配送员Id
     * @return 信息
     */
    Result getDeliveryActiveOrders(BigDecimal deliveryId);

    /**
     * 获取配送员历史订单
     * @param deliveryId 配送员Id
     * @return 信息
     */
    Result getDeliveryHistoryOrders(BigDecimal deliveryId);
}
