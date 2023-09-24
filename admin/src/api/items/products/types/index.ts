export interface ProductsData {
  productId: number;
  categoryId: number;
  imageId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  productStock: number;
  productStatus: string;
  productDetalis: string;
}
export interface GetAllProductsResponseDataReq extends PageRes {
  productList: Array<ProductsData>;
}
export type GetAllProductsResponseData = ApiResponseData<GetAllProductsResponseDataReq>;
export interface Page {
  pageSize: number;
  pageNum: number;
}

export interface AddProductsReq {
  categoryId: number;
  imageId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  productStock: number;
  productStatus: string;
  productDetails: string;
}

export interface DeleteProductsReq {
  productId: number;
}
export interface SelectProductByProductNameReq {
  productName: string;
}

export interface ModProductsReq extends AddProductsReq {
  productId: number;
}
