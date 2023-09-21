import "uni-ajax";

declare module "uni-ajax" {
   export type AjaxResult<T> = keyof CustomResponse extends never ? ApiResponseData<T> : CustomResponse<T>
   export interface AjaxInvoke {
    <T = any, R = ApiResponseData<T>>(config?: AjaxRequestConfig): Promise<R>
    <T = any, R = ApiResponseData<T>>(url?: string, data?: Data, config?: AjaxRequestConfig): Promise<R>
  }
}

export {};