import type {ListItemData} from "@/api/user-order/types";
export interface DeliveryOrdersActiveReq {
    deliveryId:number;

}

export interface DeliveryOrdersData {
    orderId: number;
    userId:number;
    userName:string;
    userPhone:string;
    orderNumber: string;
    orderDate: string;
    totalAmount: number;
    orderStatus: string;
    items: Array<ListItemData>;
}
export type DeliveryOrdersRes=Array<DeliveryOrdersData>; 

export interface OrdersCompleteReq {
    orderId:number;
}

export interface OrdersCompleteRes {
    "orderId": number,
    "orderStatus":string,
    "orderNumber": number
}