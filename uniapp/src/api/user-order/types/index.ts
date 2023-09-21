export interface ListItemData {
  productId: number;
  productName: string;
  totalAmount: number;
  productQuantity: number;
}
export interface ActiveResponseData {
  orderId: number;
  orderNumber: string;
  orderDate: string;
  totalAmount: number;
  deliveryPhone: string;
  deliveryName:string;
  orderStatus: string;
  items: Array<ListItemData>;
}
export type ActiveRes =Array<ActiveResponseData>
export interface ActiveResponseReq {
  userId:number;
}

export type HistoryResponseData =Array<ActiveResponseData>;

export interface ConfirmRequestData {
  orderId: number;
}
export type ConfirmResponseData =Array<{
  orderId: number;
  orderNumber: string;
  orderStatus: string;
}>

export interface NotReceivedRequestData extends ConfirmRequestData {}

export type NotReceivedResponseData =Array<{
    orderId:number;
    orderNumber:string;
    orderStatus:string;
    deliveryPhone:string;
    AdminPhone:string;
}>

