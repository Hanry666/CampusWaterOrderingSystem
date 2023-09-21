package band.zhaozhao.controller;

import band.zhaozhao.service.HomeProductService;
import band.zhaozhao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@RestController
@RequestMapping("/homeProduct")
public class HomeProductController {

    @Autowired
    private HomeProductService homeProductService;

    /**
     * 查看商品分类接口
     * @param categoryId 分类ID
     * @return 返回前端接口
     */
    @GetMapping("/productCategories")
    public Result productCategories(@RequestParam("categoryId") Integer categoryId){
        return homeProductService.productCategories(categoryId);
    }
}

