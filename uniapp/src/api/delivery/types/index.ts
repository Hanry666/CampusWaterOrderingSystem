export interface DeliveryAvailableData {
    "orderId": number,
    "orderPrice": number,
    "orderNumber": number,
    "orderStatus": string,
    "campus": string,
    "building": string,
    "houseNumber": string,
    "userName": string,
    "userPhone": string
}

export type DeliveryAvailableRes=Array<DeliveryAvailableData>


export interface DeliveryClaimReq {
    deliveryId:number
    orderNumber:number
}

