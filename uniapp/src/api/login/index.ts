import ajax from '@/utils/request';
import type * as Login from "./types/index";
/**
 * 用户登录
 */
export function getLoginApi(data:Login.LoginRequestData){
    return ajax<Login.LoginResponseData>({
        url:"/users/login",
        method:"POST",
        params:data
    })
}
/**
 * 用户注册
 * @param data 
 */
export function getRegApi(data:Login.RegRequestData){
    return ajax<Login.RegResponseData>({
        url:"/users/register",
        method:"POST",
        params:data,
    })
}
/**
 * 验证token
 */
export function verifyTokenApi(data:Login.VerifyTokenReq){
    return ajax<Login.VerifyTokenResData>({
        url:"users/verifyToken",
        method:"GET",
        params:data
    })
}

/**
 * 配送员注册
 */
export function deliveryRegApi(data:Login.DeliveryRegReq){
    return ajax({
        url:"delivery/register",
        method:"POST",
        params:data
    })
}

/**
 * 配送员登录
 */
export function deliveryLoginApi(data:Login.DeliveryLoginReq){
    return ajax<Login.DeliveryLoginRes>({
        url:"delivery/login",
        method:"POST",
        params:data
    })
}