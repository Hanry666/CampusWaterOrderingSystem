package band.zhaozhao.controller;

import band.zhaozhao.service.CartProductService;
import band.zhaozhao.service.ShoppingCartService;
import band.zhaozhao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-21
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

        @Autowired
        private ShoppingCartService shoppingCartService;

        @Autowired
        private CartProductService cartProductService;

        /**
         * 添加购物车商品
         * @param userId 用户ID
         * @param productId 商品ID
         * @param productQuantity 商品数量
         * @return 返回前端数据
         */
        @PostMapping("/addProduct")
        public Result addProducts(@RequestParam("userId") BigDecimal userId,
                                  @RequestParam("productId")BigDecimal productId,
                                  @RequestParam("productQuantity")BigDecimal productQuantity){
            return shoppingCartService.addProducts(userId,productId,productQuantity);
        }

    /**
     * 删除购物车商品
     * @param productId 商品ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteProduct")
        public Result deleteProduct(@RequestParam("productId")BigDecimal productId){
            return cartProductService.deleteProduct(productId);
        }

    /**
     * 商品结算接口
     * @param userId 用户ID
     * @param addressId 地址ID
     * @return 返回前端数据
     */
    @PostMapping("/checkout")
        public Result checkOutCartProduct(@RequestParam("userId")BigDecimal userId,
                                          @RequestParam("addressId")BigDecimal addressId ){
            return cartProductService.checkOutCartProduct(userId,addressId);
        }
}

