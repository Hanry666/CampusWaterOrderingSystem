import type * as Delivery from "./types/index";
import ajax from "@/utils/request";
/**
 * 配送员抢单大厅列表
 */
export function getdeliveryAvailableApi(){
    return ajax<Delivery.DeliveryAvailableRes>({
        url:"orders/delivery/available",
        method:"GET",
    })
}
/**
 * 抢单功能接口
 */
export function deliveryClaimApi(data:Delivery.DeliveryClaimReq){
    return ajax({
        url:"orders/delivery/claim",
        method:"POST",
        params:data
    })
}