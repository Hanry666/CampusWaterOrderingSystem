package band.zhaozhao.service.impl;

import band.zhaozhao.entity.OrderProduct;
import band.zhaozhao.entity.Orders;
import band.zhaozhao.mapper.*;
import band.zhaozhao.service.OrdersService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private DeliveryMapper deliveryMapper;

    @Override
    public Result addOrders(BigDecimal userId, BigDecimal productId, LocalDateTime orderDate, BigDecimal productQuantity,BigDecimal addressId) {
        //查找下单产品的价格
        BigDecimal productPrice = productMapper.selectProductPriceByProductId(productId);
        Orders orders = new Orders();
        OrderProduct orderProduct = new OrderProduct();
        //给订单添加总价钱商品金额+商品金额的6%
        orders.setTotalAmount((productPrice.multiply(productQuantity).add (productPrice.multiply(productQuantity).multiply(BigDecimal.valueOf(0.06)))));
        orderProduct.setOrderPrice((productPrice.multiply(productQuantity).multiply(BigDecimal.valueOf(0.02))));
        //新建订单
        Integer addOrder = ordersMapper.addOrderByUserId(userId,productId,orderDate,productQuantity,orders.getTotalAmount(),addressId);
        if (addOrder == null){
            return Result.error("下单失败",201,null);
        }
        //获取新添加订单的Id
        List<Orders> orderList = ordersMapper.selectOrderByUser(userId,orderDate);
        for (Orders order: orderList) {
            BigDecimal orderId = order.getOrderId();
            //添加商品信息到orderProduct中
            orderProductMapper.addOrderProduct(orderId,productId,productQuantity,orderProduct.getOrderPrice(),addressId);
        }
        if (orderList.isEmpty()) {
            return Result.error("下单失败",201,null);
        }
        //查看库存是否够用
        BigDecimal productStock = productMapper.selectProductStockByProduct(productId);
        BigDecimal stock = productStock.subtract(productQuantity);
        if(stock.compareTo(BigDecimal.ZERO) < 0) {
            return Result.error("库存不够",201,null);
        }
        //更新库存
        Integer newStock = productMapper.upadteProductStock(stock, productId);
        if (newStock == 0) {
            return Result.error("更新库存失败",201,null);
        }
        return Result.success("下单成功",200,orderList);
    }

    @Override
    public Result getUserActiveOrder(BigDecimal userId) {
        List<Orders> ordersList = null;
        try {
            ordersList = ordersMapper.selectActiveOrdersByUserId(userId);
            for (Orders orders : ordersList) {
                List<OrderProduct> orderProductList = orderProductMapper.selectByOrderId(orders.getOrderId());
                orders.setItems(orderProductList);
            }
            if (!ordersList.isEmpty()) {
                return Result.error("查询成功", 200, ordersList);
            }
        } catch (Exception e) {
            return Result.success("查询失败", 201, ordersList);
        }

        return Result.success("无数据", 200, ordersList);
    }

    @Override
    public Result getUserHistoryOrder(BigDecimal userId) {
        String orderStatus = "已收货";
        List<Orders> ordersList = null;
        try {
            ordersList = ordersMapper.selectOrdersByUserId(userId, orderStatus);
            for (Orders orders : ordersList) {
                List<OrderProduct> orderProductList = orderProductMapper.selectByOrderId(orders.getOrderId());
                orders.setItems(orderProductList);
            }

            if (!ordersList.isEmpty()) {
                return Result.error("查询成功", 200, ordersList);
            }
        } catch (Exception e) {
            return Result.success("查询失败", 201, ordersList);
        }
        return Result.success("无数据",200,ordersList);
    }

    @Override
    public Result updateOrderStatusByOrderId(BigDecimal orderId) {
        String orderStatus = "已收货";
        Integer integer = ordersMapper.updateStatusByOrderId(orderId, orderStatus);
        if (integer == null) {
            return Result.error("收货失败", 201, null);
        }
        List<Orders> ordersList = ordersMapper.selectByOrderId(orderId);
        BigDecimal orderPrice = orderProductMapper.selectOrderPriceByOrderId(orderId);
        BigDecimal deliveryId = ordersMapper.selectDeliveryIdByOrderId(orderId);
        BigDecimal balance = deliveryMapper.selectBalanceByDeliveryId(deliveryId);
        BigDecimal newBalance = orderPrice.add(balance);
        Integer nbalance =  deliveryMapper.updateBalanceByDeliveryId(deliveryId,newBalance);
        if(nbalance.equals(0)){
            return Result.error("送货员收钱失败",201,null);
        }
        return Result.success("收货成功", 200 ,ordersList);
    }

    @Override
    public Result updateOrderNotReceivedByOrderId(BigDecimal orderId) {
        String orderStatus = "未收到货";
        Integer integer = ordersMapper.updateStatusByOrderId(orderId, orderStatus);
        if (integer == null) {
            return Result.error("未收货失败", 201, null);
        }
        List<Orders> ordersList = ordersMapper.selectByOrderId(orderId);
        return Result.success("未收货成功", 200 ,ordersList);
    }

    @Override
    public Result getAllAvailableOrders() {
       List<OrderProduct> orderProductList = orderProductMapper.selectAllOrders();
       return Result.success("获取订单成功",200,orderProductList);
    }

    @Override
    public Result claimOrders(BigDecimal deliveryId, BigDecimal orderNumber) {
      Integer order = ordersMapper.claimOrders(deliveryId,orderNumber);
      if (order.equals(0)) {
          return Result.error("接单失败",201,null);
      }
      return Result.success("接单成功",200,null);
    }

    @Override
    public Result getDeliveryActiveOrders(BigDecimal deliveryId) {
        String orderStatus = "已接单";
        List<Orders> ordersList = null;
        try {
            ordersList = ordersMapper.selectOrdersByDeliveryId(deliveryId, orderStatus);
            for (Orders orders : ordersList) {
                List<OrderProduct> orderProductList = orderProductMapper.selectByOrderId(orders.getOrderId());
                orders.setItems(orderProductList);
            }
            if (!ordersList.isEmpty()) {
                return Result.error("查询成功", 200, ordersList);
            }
        }catch (Exception e) {
            return Result.success("查询失败", 200, ordersList);
        }
        return Result.success("无数据",200,ordersList);
    }

    @Override
    public Result getDeliveryHistoryOrders(BigDecimal deliveryId) {
        String orderStatus = "已收货";
        List<Orders> ordersList = null;
        try {
            ordersList = ordersMapper.selectOrdersByDeliveryId(deliveryId, orderStatus);
            for (Orders orders : ordersList) {
                List<OrderProduct> orderProductList = orderProductMapper.selectByOrderId(orders.getOrderId());
                orders.setItems(orderProductList);
            }
            if (!ordersList.isEmpty()) {
                return Result.success("查询成功", 200, ordersList);
            }
        } catch (Exception e) {
            return Result.error("查询失败", 201, ordersList);
        }
        return Result.success("无数据", 200, ordersList);
    }



}
