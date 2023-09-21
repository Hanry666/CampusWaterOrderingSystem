import ajax from "@/utils/request";
import type * as Address from "./types"
/**
 * 返回所有用户的地址
 */
export function getAllUserAddressApi(data:Address.GetAllUserAddressReq){
    return ajax<Address.GetAllUserAddressRes>({
        url:"address/getAllUserAddress",
        params:data
    })
}
/**
 * 选择已有地址
 */
export function addOrderAddressIdApi(data:Address.addOrderAddressIdReq){
    return ajax<null>({
        url:"address/choseUserAddress",
        method:"POST",
        params:data
    })
}
/**
 * 新增地址接口
 */
export function addUserAddressApi(data:Address.AddUserAddressReq){
    return ajax<null>({
        url:"address/addUserAddress",
        method:"POST",
        params:data
    })
}

/**
 * 修改地址接口
 */
export function modAddressApi(data:Address.ModAddressReq){
    return ajax<null>({
        url:"address/modAddress",
        method:"POST",
        params:data
    })
}

/**
 * 删除地址接口
 */
export function deleteAddressApi(data:Address.DeleteAddressReq){
    return ajax<null>({
        url:"address/deleteAddress",
        method:"DELETE",
        params:data
    })
}