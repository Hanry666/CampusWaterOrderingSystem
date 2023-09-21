package band.zhaozhao.controller;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.service.HomePageProductService;
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
@RequestMapping("/homePageProduct")
public class HomePageProductController {

    @Autowired
    public HomePageProductService homePageProductService;

    /**
     * 展示所有滚动商品接口
     * @return 返回前端数据
     */
    @GetMapping("/scrollingProducts")
    public Result  scrollingProducts(){
        return homePageProductService.selectAllProduct();
    }

    /**
     * 添加滚动商品接口
     * @param productId 商品ID
     * @param imageUrl 图片地址
     * @return 返回前端数据
     */
    @PostMapping("/addHomePageProduct")
    public Result addHomePageProduct(@RequestParam("productId")BigDecimal productId,
                                     @RequestParam("imageUrl")String imageUrl){
        return homePageProductService.addHomePageProduct(productId,imageUrl);
    }

    /**
     * 删除滚动商品接口
     * @param carouselId 轮播ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteHomePageProduct")
    public Result deleteHomePageProduct(@RequestParam("carouselId")BigDecimal carouselId){
        return homePageProductService.deleteHomePageProduct(carouselId);
    }

    /**
     * 修改滚动商品接口
     * @param carouselId 轮播ID
     * @param productId 商品ID
     * @param imageUrl 图片地址
     * @return 返回前端数据
     */
    @PostMapping("/modHomePageProduct")
    public Result modHomePageProduct(@RequestParam("carouselId")BigDecimal carouselId,
                                        @RequestParam("productId")BigDecimal productId,
                                     @RequestParam("imageUrl")String imageUrl){
        return homePageProductService.modHomePageProduct(carouselId,productId,imageUrl);
    }

    /**
     * 按名字获取滚动商品接口
     * @param query 分页数据
     * @return 返回前端数据
     */
    @PostMapping("/getAllHomePageProduct")
    public Result getAllHomePageProduct(@RequestBody QueryPageParam query){
        return homePageProductService.getAllHomePageProduct(query);
    }
}

