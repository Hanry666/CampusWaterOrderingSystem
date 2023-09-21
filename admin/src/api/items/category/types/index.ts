export interface CategoryData {
  "categoryId": number
  "categoryName": string
  "isDelete": number
}

export type  getAllCategoriesRes =ApiResponseData<Array<CategoryData>> 


export interface addCategoryReq {
  categoryName:string;
}

export interface deleteCategoryReq {
  categoryId:number;
}
 
export interface modCategoryReq {
  categoryId:number;
  categoryName:string;
}