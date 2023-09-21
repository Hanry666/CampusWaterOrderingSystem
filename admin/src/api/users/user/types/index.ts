export interface Page {
  "pageNum":number
  "pageSize":number
}

export interface GetAllUsersData {
  "userId": number
  "userName": string
  "userPhone":string
  "userPassword": string
  "gender": string
  "addressId": number
  "balance": number
  "role": string
  "priority": number,
  "isDelete": number
}

export interface GetAllUsersDataReq extends PageRes {
  usersList:Array<GetAllUsersData>
}

export type GetAllUsersRes=ApiResponseData<GetAllUsersDataReq>

export interface SelectUsersByuserNameReq {
  "userName":string;
}

export interface DeleteUserReq {
  userId:number;
}

