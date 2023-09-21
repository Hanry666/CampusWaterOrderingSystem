package band.zhaozhao.controller;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.service.ProductService;
import band.zhaozhao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查看商品详情
     * @param productId  商品ID
     * @return 返回前端数据
     */
    @GetMapping("/productDetails")
    public Result productDetails(@RequestParam("productId") Integer productId){
        return productService.getProductDetailsById(productId);
    }

    /**
     * 添加商品
     * @param categoryId 分类ID
     * @param imageId 图片ID
     * @param productName 商品名
     * @param productDescription 商品描述
     * @param productPrice 商品价格
     * @param productStock 商品库存
     * @param productStatus 商品状态
     * @param productDetails 商品详情
     * @return 返回前端数据
     */
    @PostMapping("/addProducts")
    public Result addProducts(@RequestParam("categoryId") BigDecimal categoryId,
                                @RequestParam("imageId")BigDecimal imageId,
                              @RequestParam("productName")String productName,
                              @RequestParam("productDescription")String productDescription,
                              @RequestParam("productPrice")BigDecimal productPrice,
                              @RequestParam("productStock")BigDecimal productStock,
                              @RequestParam("productStatus")String productStatus,
                              @RequestParam("productDetails")String productDetails){
        return productService.addProducts(categoryId,imageId,productName,productDescription,productPrice,productStock,productStatus,productDetails);
    }

    /**
     * 按商品ID删除商品
     * @param productId 商品ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteProducts")
    public Result deleteProducts(@RequestParam("productId")BigDecimal productId){
        return productService.deleteProductsByProductId(productId);
    }

    /**
     * 修改商品数据
     * @param productId 商品ID
     * @param categoryId 分类ID
     * @param imageId 图片ID
     * @param productName 商品名
     * @param productDescription 商品描述
     * @param productPrice 商品价格
     * @param productStock 商品库存
     * @param productStatus 商品状态
     * @param productDetails 商品详情
     * @return 返回前端数据
     */
    @PostMapping("/modProducts")
    public Result modProducts(@RequestParam("productId")BigDecimal productId,
                                @RequestParam("categoryId") BigDecimal categoryId,
                             @RequestParam("imageId")BigDecimal imageId,
                             @RequestParam("productName")String productName,
                             @RequestParam("productDescription")String productDescription,
                             @RequestParam("productPrice")BigDecimal productPrice,
                             @RequestParam("productStock")BigDecimal productStock,
                             @RequestParam("productStatus")String productStatus,
                             @RequestParam("productDetails")String productDetails){
        return productService.modProducts(productId,categoryId,imageId,productName,productDescription,productPrice,productStock,productStatus,productDetails);
    }

    /**
     * 获取所有商品
     * @param query 分页数据
     * @return 返回前端数据
     */
    @PostMapping("/getAllProducts")
    public Result getAllProducts(@RequestBody QueryPageParam query){
        return productService.getAllProducts(query);
    }

    /**
     * 按名字获取所有商品
     * @param query 分页数据
     * @return 返回前端数据
     */
    @PostMapping("/selectProductByProductName")
    public Result selectProductByProductName(@RequestBody QueryPageParam query){
        return productService.selectProductByProductName(query);
    }

}

