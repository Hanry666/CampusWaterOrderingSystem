import ajax from "@/utils/request";
import type * as UserItem from "./types/index";
/**
 * 获取轮播图商品
 */
export function getScrollingProductsApi(){
    return ajax<UserItem.ScrollingProductsResponseData>({
        url:"homePageProduct/scrollingProducts",
        method:"GET",
    })
}
/**
 * 获取商品分类
 */
export function getProductCategoriesApi(){
    return ajax<UserItem.ProductCategoriesResponseData>({
        url:"category/getAllCategories",
        method:"GET"
    })
}
/**
 * 获取商品
 */
export function getProductCategoriesItemApi(data:UserItem.ProductCategoriesItemRequestData){
    return ajax<UserItem.ProductCategoriesItemResponseData>({
        url:"homeProduct/productCategories",
        method:"GET",
        params:data
    })
}
/**
 * 获取商品详情
 */
export function getProductDetailsApi(data:UserItem.ProductDetailsRequestData){
    return ajax<UserItem.ProductDetailsResponseData>({
        url:"product/productDetails",
        method:"GET",
        params:data
    })
}
/**
 * 用户下单
 */
export function getUsersOrdersApi(data: UserItem.UsersOrdersRequestData){
    return ajax<UserItem.UsersOrdersResponseData>({
        url:"users/orders",
        method:"POST",
        data
    })
}