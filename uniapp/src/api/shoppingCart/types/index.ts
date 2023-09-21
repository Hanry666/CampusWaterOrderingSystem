export interface AddProductReq {
  userId: number;
  productId: number;
  productQuantity: number;
}

export interface ShoppingCarData {
  "cartId": number
  "productId": number
  "productQuantity": number
  "cartPrice": number
  "productName": string
}
export type ShoppingCarDataArr=Array<ShoppingCarData>;

export type AddProductRes = Array<ShoppingCarData>;

export interface DeleteProductReq {
  cartId: string;
}

export interface CheckoutReq {
  userId: number;
  addressId:number;
}
export interface OrderData {
//   orderId: 173
// orderNumber: 9347228126
// orderStatus: "已下单"
// totalAmount: 48.76
orderId: number
orderNumber: number
orderStatus: string
totalAmount: number
}
export type CheckoutRes = Array<OrderData>;
export interface ShoppingCartReq {
  userId:number;
}

export interface ShoppingCartRes {
  cartProduct:ShoppingCarDataArr;
  totalAmount:number;
}

export interface PayReq {
  orderNumber:number;
  totalAmount:number;
}