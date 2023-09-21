import ajax from "@/utils/request"
import type * as Shopping from "./types/index"
/**
 * 商品加入购物车
 */
export  function addProductApi(data:Shopping.AddProductReq){
    return ajax({
        url:"shoppingCart/addProduct",
        method:"POST",
        params:data
    })
}
/**
 * 购物车删除物品
 */
export function deleteProductApi(data:Shopping.DeleteProductReq){
    return ajax<null>({
        url:"shoppingCart/deleteProduct",
        method:"DELETE",
        params:data
    })
}

/**
 * 购物车结算功能
 */
export function checkoutApi(data:Shopping.CheckoutReq){
    return ajax<Shopping.CheckoutRes>({
        url:"shoppingCart/checkout",
        method:"POST",
        params:data
    })
}

/**
 * 查看购物车功能
 */
export function getShoppingCartApi(data:Shopping.ShoppingCartReq){
    return ajax<Shopping.ShoppingCartRes>({
        url:"users/shoppingCart",
        method:"GET",
        params:data
    })
}
export function PayApi(data:Shopping.PayReq){
    return ajax<null>({
        url:"users/payOrders",
        method:"GET",
        params:data
    })
}