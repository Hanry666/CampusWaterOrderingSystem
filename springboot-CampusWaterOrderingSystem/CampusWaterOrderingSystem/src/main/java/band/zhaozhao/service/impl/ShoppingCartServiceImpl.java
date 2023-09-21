package band.zhaozhao.service.impl;

import band.zhaozhao.entity.CartProduct;
import band.zhaozhao.entity.ShoppingCart;
import band.zhaozhao.mapper.CartProductMapper;
import band.zhaozhao.mapper.ProductMapper;
import band.zhaozhao.mapper.ShoppingCartMapper;
import band.zhaozhao.service.ShoppingCartService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private CartProductMapper cartProductMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result addProducts(BigDecimal userId, BigDecimal productId, BigDecimal productQuantity) {
        List<CartProduct> cartProducts = null;
        try {
            //获取用户的购物车ID
            if (shoppingCartMapper.selectByUserId(userId) == null) {
                //添加购物车的信息
                shoppingCartMapper.addCart(userId);
            }
            //获取用户购物车的ID
            Integer cartId = shoppingCartMapper.selectByUserId(userId);
            //获得商品价格
            BigDecimal productPrice = productMapper.selectProductPriceByProductId(productId);
            //获取商品的库存
            BigDecimal productStock = productMapper.selectProductStockByProduct(productId);
            //计算是否库存是否够用
            BigDecimal stock = productStock.subtract(productQuantity);
            if (stock.compareTo(BigDecimal.ZERO) < 0) {
                return Result.success("库存不足", 201, null);
            }
            //计算购物车的价格
            BigDecimal cartPrice = productPrice.multiply(productQuantity);
            //添加到用户购物车
            Integer addProducts = cartProductMapper.addProducts(cartId, productId, productQuantity, cartPrice);
            if (!addProducts.equals(0)) {
                return Result.success("添加成功", 200, cartProducts);
            }
        } catch (Exception e) {
            return Result.error("添加失败", 201, cartProducts);
        }
        return Result.success("添加成功", 200, cartProducts);
    }



}
