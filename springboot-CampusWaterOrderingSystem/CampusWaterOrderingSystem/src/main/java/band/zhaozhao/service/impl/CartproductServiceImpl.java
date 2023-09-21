package band.zhaozhao.service.impl;

import band.zhaozhao.entity.Cart;
import band.zhaozhao.entity.CartProduct;
import band.zhaozhao.entity.Orders;
import band.zhaozhao.mapper.*;
import band.zhaozhao.service.CartProductService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-21
 */
@Service
public class CartproductServiceImpl extends ServiceImpl<CartProductMapper, CartProduct> implements CartProductService {

    @Autowired
    private CartProductMapper cartProductMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderProductMapper orderProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result deleteProduct(BigDecimal productId) {
        try {
            //通过传进的商品Id删除
            if (!cartProductMapper.updateProduct(productId).equals(0)) {
                return Result.success("删除成功", 200, null);
            }
        } catch (Exception e) {
            return Result.error("删除失败", 201, null);

        }
            return Result.success("删除成功",200,null);
    }

    @Override
    public Result showUserCartByUserId(BigDecimal userId) {
        List<CartProduct> cartProducts = null;
        Cart cart1 = new Cart();
        try {
            //通过用户ID获取购物车
            Integer cartId = shoppingCartMapper.selectByUserId(userId);
            //获取购物车的信息
            cartProducts = cartProductMapper.selectAllByUserId(userId);
            BigDecimal totalAmount = cartProductMapper.getSumPrice(cartId);
            //添加到购物车类返回给前端
            cart1.setCartProduct(cartProducts);
            cart1.setTotalAmount(totalAmount);
            if (cartProducts.isEmpty()){
                return Result.success("查看成功，无数据",200,cart1);
            }
                return Result.success("查看成功", 200, cart1);
        } catch (Exception e) {
            return Result.error("查看失败", 201, cart1);
        }

    }

    @Override
    public Result checkOutCartProduct(BigDecimal userId, BigDecimal addressId) {
        //通过用户Id查看购物车Id
        Integer cartId = shoppingCartMapper.selectByUserId(userId);
        //从数据库获取总价格
        BigDecimal totalAmount = cartProductMapper.getSumPrice(cartId);
        //获取购物车信息
        List<CartProduct> cartProducts = cartProductMapper.selectAllByUserId(userId);
        //计算商品和平台抽成
        BigDecimal subtotal = totalAmount.multiply(BigDecimal.valueOf(0.06)).add(totalAmount);
        //计算配送员佣金
        BigDecimal orderPrice = subtotal.multiply(BigDecimal.valueOf(0.02));
        //获取当前时间填入订单
        LocalDateTime time = LocalDateTime.of(LocalDate.now(),LocalTime.now());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatTime = format.format(time);
        //更改格式存入订单
        LocalDateTime orderDate = LocalDateTime.parse(formatTime, format);
        List<Orders> orderList = null;
        int integer = 0;
        for (CartProduct cartProduct : cartProducts) {
            BigDecimal productId = cartProduct.getProductId();
            BigDecimal productQuantity = BigDecimal.valueOf(cartProduct.getProductQuantity());
            if(integer < 1) {
                //把购物车中的商品信息等加入到订单，值存一次订单信息
                ordersMapper.addOrderByUserId(userId, productId, orderDate, productQuantity, subtotal, addressId);
                integer = 10;
            }
                orderList = ordersMapper.selectOrderByUserId(userId, orderDate);
                for (Orders order : orderList) {
                    BigDecimal orderId = order.getOrderId();
                    //把所有的购物车中的商品循环进入到OrderProduct表内
                    orderProductMapper.addOrderProduct(orderId, productId, productQuantity, orderPrice, addressId);
                    if (orderList.isEmpty()) {
                        return Result.error("下单失败", 201, null);
                    }
                    //查看库存是否够购物车所购买的数量
                    BigDecimal productStock = productMapper.selectProductStockByProduct(productId);
                    BigDecimal stock = productStock.subtract(productQuantity);
                    if (stock.compareTo(BigDecimal.ZERO) < 0) {
                        return Result.error("库存不够", 201, null);
                    }
                    //更新商品的库存
                    Integer newStock = productMapper.upadteProductStock(stock, productId);
                    if (newStock == 0) {
                        return Result.error("更新库存失败", 201, null);
                    }
                    Integer cart = cartProductMapper.updateProduct(productId);
                }
            }

        return Result.error("下单成功", 200, orderList);

    }




}
