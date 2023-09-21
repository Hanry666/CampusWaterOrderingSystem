export interface LoginRequestData {
  userPhone: string;
  userPassword: string;
}

export interface LoginResponseData {
  userId: number;
  userName: string;
  token: string;
}

export interface VerifyTokenResData {
  id: number;
  name: string;
  phone: string;
  role: string;
}
export interface RegRequestData {
  /**
   * 用户名
   */
  userName: string;
  /**
   * 手机号
   */
  userPhone: string;
  /**
   * 密码
   */
  userPassword: string;
  /**
   * 性别
   */
  gender: string;
  /**
   * 角色
   */
  role: string;
}

export interface RegResponseData {
  userId: string;
  userName: string;
  phone: string;
}
export interface VerifyTokenReq {
  token: string;
}

export interface ModRequestData extends RegRequestData {}

export interface DeliveryRegReq {
  deliveryName: string;
  deliveryPhone: string;
  deliveryPassword: string;
  gender: string;
  role: string;
}
export interface DeliveryRegRes {}

export interface DeliveryLoginReq {
  deliveryPhone: string;
  deliveryPassword: string;
}

export interface DeliveryLoginRes {
  deliveryId: number;
  deliveryName: string;
  token:string;
}
