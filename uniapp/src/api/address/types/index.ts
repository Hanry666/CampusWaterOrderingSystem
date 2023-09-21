export interface GetAllUserAddressReq {
    userId:number
}

export interface GetAllUserAddressData {
    "addressId": number
    "campus": string
    "building": string
    "houseNumber": string
}

export type GetAllUserAddressRes=Array<GetAllUserAddressData>;

export interface addOrderAddressIdReq {
    addressId:number;
    orderId:number;
}

export interface AddUserAddressReq {
    userId:number;
    campus:string;
    building:string;
    houseNumber:string;
}

export interface ModAddressReq extends GetAllUserAddressData {}

export interface DeleteAddressReq {
    addressId:number
}