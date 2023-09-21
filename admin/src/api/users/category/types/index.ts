export interface Page {
  "pageNum":number
  "pageSize":number
}

export interface GetAllDeliveryData {
  "deliveryId": number
  "deliveryName": string
  "deliveryPhone":string
  "deliveryPassword": string
  "gender": string
  "addressId": number
  "balance": number
  "role": string
  "isDelete": number
}
export interface GetAllDeliveryDataRes extends PageRes {
  deliveryList:Array<GetAllDeliveryData>
}
export type GetAllDeliveryRes=ApiResponseData<GetAllDeliveryDataRes>

export interface SelectUsersByDeliveryNameReq {
  "deliveryName":string;
}

export interface DeleteDeliveryReq {
  deliveryId:number;
}

