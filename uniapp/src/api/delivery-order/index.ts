import ajax from "@/utils/request"
import type * as Order from "./types"

export function deliveryOrdersActiveApi(data:Order.DeliveryOrdersActiveReq){
    return ajax<Order.DeliveryOrdersRes>({
        url:"delivery/orders/active",
        method:"GET",
        params:data
    })
}

export function deliveryOrdersHistoryApi(data:Order.DeliveryOrdersActiveReq){
    return ajax<Order.DeliveryOrdersRes>({
        url:"delivery/orders/history",
        method:"GET",
        params:data
    })
}

export function deliveryOrdersComplete(data:Order.OrdersCompleteReq){
    return ajax<Order.OrdersCompleteRes>({
        url:"delivery/orders/complete",
        method:"POST",
        params:data
    })
}