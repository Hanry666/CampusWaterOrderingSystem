package band.zhaozhao.mapper;

import band.zhaozhao.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
public interface OrdersMapper extends BaseMapper<Orders> {

    /**
     * 通过userId添加订单
     * @param userId 用户Id
     * @param productId 商品Id
     * @param orderDate 订单时间
     * @param productQuantity 订单数量
     * @param totalAmount 总金额
     * @param addressId 地址Id
     * @return 信息
     */
    Integer addOrderByUserId(BigDecimal userId, BigDecimal productId, LocalDateTime orderDate, BigDecimal productQuantity, BigDecimal totalAmount,BigDecimal addressId);

    /**
     * 按订单号查看用户ID
     * @param orderNumber 订单号
     * @return 用户ID
     */
    BigDecimal selectUserIdByOrderNmber(BigDecimal orderNumber);

    /**
     * 更新订单状态
     * @param orderNumber 订单号
     * @param orderStatus 订单状态
     * @return 信息
     */
    Integer updateOrderStatusByOrderNumber(BigDecimal orderNumber, String orderStatus);

    /**
     * 按订单号查询订单状态
     * @param orderNumber 订单号
     * @return 订单状态
     */
    String selectOrderStatusByOrderNumber(BigDecimal orderNumber);

    /**
     * 按照用户ID 和 订单日期查看订单啊
     * @param userId 用户Id
     * @param orderDate 订单日期
     * @return 订单信息
     */
    List<Orders> selectOrderByUser(BigDecimal userId, LocalDateTime orderDate);

    /**
     * 按照用户ID和订单状态查找信息
     * @param userId 用户ID
     * @param orderStatus 订单状态
     * @return 订单信息
     */
    List<Orders> selectOrdersByUserId(BigDecimal userId, String orderStatus);

    /**
     * 按照订单Id查找订单
     * @param orderId 订单ID
     * @return 订单信息
     */
    List<Orders> selectByOrderId(BigDecimal orderId);

    /**
     * 按订单ID和订单状态来更新订单
     * @param orderId 订单Id
     * @param orderStatus 订单状态
     * @return 信息
     */
    Integer updateStatusByOrderId(BigDecimal orderId, String orderStatus);

    /**
     * 确认订单按找订单号
     * @param deliveryId 配送员ID‘
     * @param orderNumber 订单号
     * @return 信息
     */
    Integer claimOrders(BigDecimal deliveryId, BigDecimal orderNumber);

    /**
     * 按配送员Id和订单状态查看订单
     * @param deliveryId 配送员ID
     * @param orderStatus 订单状态
     * @return 订单信息
     */
    List<Orders> selectOrdersByDeliveryId(BigDecimal deliveryId, String orderStatus);

    /**
     * 按订单ID查看配送员Id
     * @param orderId 订单ID
     * @return 配送员ID
     */
    BigDecimal selectDeliveryIdByOrderId(BigDecimal orderId);

    /**
     * 按用户Id和订单日期查看订单信息
     * @param userId 用户ID
     * @param orderDate 订单日期
     * @return 订单信息
     */
    List<Orders> selectOrderByUserId(BigDecimal userId, LocalDateTime orderDate);

    /**
     * 查看用户正在进行的订单
     * @param userId 用户Id
     * @return 订单信息
     */
    List<Orders> selectActiveOrdersByUserId(BigDecimal userId);
}
