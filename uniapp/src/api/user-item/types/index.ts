
export type ScrollingProductsResponseData =Array<{
  /**
   * 商品ID
   */
  carouselId:number;
  /**
   * 商品种类
   */
  categoryName: string;
  /**
   * 商品ID
   */
  productId: number;
  /**
   * 商品名称
   */
  productName: string;
  /**
   * 商品价格
   */
  producPrice: string;
  /**
   * 商品图片地址
   */
  imageUrl: string;
}> 
export type ProductCategoriesResponseData =Array<{
  categoryId: number;
  categoryName: string;
}>
export interface ProductCategoriesItemRequestData {
  categoryId:number;
}
export type ProductCategoriesItemResponseData =Array<{
  productId:number;

  productName:string;
  
  imageUrl:string;

  productPrice:number;
}>
export interface ProductDetailsRequestData {
    /**
     * 商品id
     */
    productId:number;
} 
export interface ProductDetailsResponseData {
  /**
   * 商品ID
   */
  productId: number;
  /**
   * 商品名
   */
  productName: string;
  /**
   * 商品价格
   */
  producPrice: number;
  /**
   * 商品描述
   */
  details: string;
}
export interface UsersOrdersRequestData {
    userId:number;
    items:Array<{
        /**
         * 商品id
         */
        productId:number;
        /**
         * 购买的个数
         */
        ProductQuantity:number;
        /**
         * 下单时间
         */
        ordersDate:string;
    }>
    shippingAddress:Array<{
        /**
         * 校区
         */
        campus:string;
        /**
         * 哪栋楼
         */
        building:string;
        /**
         * 门牌号
         */
        houseNumber:string;
    }>

}
export interface UsersOrdersResponseData {
    /**
     * 订单ID
     */
    orderId:number;
    /**
     * 订单号   
     */
    orderNumber:string;
    /**
     * 订单状态
     */
    orderStatus:string;
}

