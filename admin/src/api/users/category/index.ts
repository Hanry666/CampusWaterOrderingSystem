import { request } from "@/utils/service";
import type * as Delivery from "./types/index";
/**
 * 查询所有配送员接口
 */
export function getAllUsersApi(page: Delivery.Page) {
  return request<Delivery.GetAllDeliveryRes>({
    url: "delivery/getAllDeliver",
    method: "POST",
    data: page
  });
}

/**
 * 按名字查询配送员接口
 */
export function selectUsersByuserNameApi(page: Delivery.Page, data: Delivery.SelectUsersByDeliveryNameReq) {
  return request<Delivery.GetAllDeliveryRes>({
    url: "delivery/getAllDeliverByName",
    method: "POST",
    data: {
      ...page,
      param: data
    }
  });
}

/**
 * 删除配送员
 */
export function deleteUserApi(data: Delivery.DeleteDeliveryReq) {
  return request({
    url: "delivery/deleteDeliver",
    method: "DELETE",
    params: data
  });
}
