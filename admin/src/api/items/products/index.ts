import { request } from "@/utils/service";
import type * as Products from "./types/index";
/**
 * 获取全部商品
 */
export function getAllProductsApi(data: Products.Page) {
  return request<Products.GetAllProductsResponseData>({
    url: "product/getAllProducts",
    method: "POST",
    data
  });
}
/**
 * 添加商品
 */
export function addProductsApi(data: Products.AddProductsReq) {
  return request({
    url: "product/addProducts",
    method: "POST",
    params: data
  });
}
/**
 * 删除商品
 */
export function deleteProductsApi(data: Products.DeleteProductsReq) {
  return request({
    url: "product/deleteProducts",
    method: "DELETE",
    params: data
  });
}

/**
 * 按名字查商品
 */
export function selectProductByProductNameApi(page: Products.Page, param: Products.SelectProductByProductNameReq) {
  return request<Products.GetAllProductsResponseData>({
    url: "product/selectProductByProductName",
    method: "POST",
    data: {
      ...page,
      param
    }
  });
}
/**
 * 修改商品
 */
export function modProductsApi(data: Products.ModProductsReq) {
  return request({
    url: "product/modProducts",
    method: "POST",
    params: data
  });
}
