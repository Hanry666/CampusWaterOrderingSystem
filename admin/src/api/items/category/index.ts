import { request } from "@/utils/service";
import type * as Category from "./types/index";

/**
 * 获取商品分类
 */
export function getAllCategoriesApi() {
  return request<Category.getAllCategoriesRes>({
    url: "category/getAllCategories",
    method: "get"
  });
}
/**
 * 添加商品分类
 */
export function addCategoryApi(data: Category.addCategoryReq) {
  return request({
    url: "category/addCategory",
    method: "POST",
    params: data
  });
}
/**
 * 删除商品分类
 */
export function deleteCategoryApi(data: Category.deleteCategoryReq) {
  return request({
    url: "category/deleteCategory",
    method: "DELETE",
    params: data
  });
}
/**
 * 更改商品分类
 */
export function modCategoryApi(data: Category.modCategoryReq) {
  return request({
    url: "category/modCategory",
    method: "modCategory",
    params: data
  });
}
