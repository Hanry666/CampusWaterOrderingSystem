import { request } from "@/utils/service"
import type * as Login from "./types/login"

/** 获取登录验证码 */
export function getLoginCodeApi() {
  return Promise.resolve<Login.LoginCodeResponseData>({
    code: 200,
    data: "abcd",
    message:"success"
  })  
  // return request<Login.LoginCodeResponseData>({
  //   url: "login/code",
  //   method: "get"
  // })
}

/** 登录并返回 Token */
export function loginApi(data: Login.LoginRequestData) {
  return Promise.resolve<Login.LoginResponseData>({
    code:200,
    data:{
      token:'token'
    },
    message:"success"
  })
  // return request<Login.LoginResponseData>({
  //   url: "users/login",
  //   method: "post",
  //   data
  // })
}

/** 获取用户详情 */
export function getUserInfoApi() {
  return Promise.resolve<Login.UserInfoResponseData>({
    code:200,
    data:{
      roles:["DEFAULT_ROLE"],
      username:"管理员"
    },
    message:"success"
  })
  return request<Login.UserInfoResponseData>({
    url: "users/info",
    method: "get"
  })
}
