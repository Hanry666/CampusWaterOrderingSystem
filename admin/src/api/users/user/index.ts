import { request } from "@/utils/service";
import type * as User from "./types/index";
/**
 * 查询所有用户
 */
export function getAllUsersApi(page: User.Page) {
  return request<User.GetAllUsersRes>({
    url: "users/getAllUsers",
    method: "POST",
    data: page
  });
}

/**
 * 按名字查询用户接口
 */
export function selectUsersByuserNameApi(page: User.Page, data: User.SelectUsersByuserNameReq) {
  return request<User.GetAllUsersRes>({
    url: "users/selectUsersByuserName",
    method: "POST",
    data: {
      ...page,
      param: data
    }
  });
}

/**
 * 删除用户
 */
export function deleteUserApi(data: User.DeleteUserReq) {
  return request({
    url: "users/deleteUser",
    method: "DELETE",
    params: data
  });
}
