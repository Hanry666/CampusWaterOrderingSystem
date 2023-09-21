import ajax from "@/utils/request";
import type * as UserOrder from "./types/index";
/**
 * 用户正在进行的订单
 */
export function getActiveApi(data:UserOrder.ActiveResponseReq){
    return ajax<UserOrder.ActiveRes>({
        url:"users/orders/active",
        method:"GET",
        params:data
    })
}
/**
 * 用户历史订单
 */
export function getHistoryApi(data:UserOrder.ActiveResponseReq){
    return ajax<UserOrder.HistoryResponseData>({
        url:"users/orders/history",
        method:"GET",
        params:data
    })
}
/**
 * 用户确认收货接口
 */
export function getConfirmApi(data:UserOrder.ConfirmRequestData){
    return ajax<UserOrder.ConfirmResponseData>({
        url:"users/orders/confirm",
        method:"GET",
        params:data
    })
}
/**
 * 用户未收到货接口
 */
export function getNotReceivedApi(data:UserOrder.NotReceivedRequestData){
    return ajax<UserOrder.NotReceivedResponseData>({
        url:"users/orders/notReceived",
        method:"POST",
        data
    })
}
