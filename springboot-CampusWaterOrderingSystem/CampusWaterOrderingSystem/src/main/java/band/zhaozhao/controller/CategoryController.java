package band.zhaozhao.controller;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 页面获取分类接口
     * @return 返回前端数据
     */
    @GetMapping("/getAllCategories")
    public Result getAllCategories(){
        return categoryService.getAllCategories();
    }

    /**
     * 添加分类接口
     * @param categoryName 分类名
     * @return 返回前端数据
     */
    @PostMapping("/addCategory")
    public Result addCategory(@RequestParam("categoryName")String categoryName){
        return categoryService.addCategory(categoryName);
    }

    /**
     * 删除分类接口
     * @param categoryId 分类ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteCategory")
    public Result deleteCategory(@RequestParam("categoryId")BigDecimal categoryId){
        return categoryService.deleteCategory(categoryId);
    }

    /**
     * 修改分类接口
     * @param categoryId 分类ID
     * @param categoryName 分类名
     * @return 返回前端数据
     */
    @PostMapping("/modCategory")
    public Result modCategory(@RequestParam("categoryId")BigDecimal categoryId,
                                @RequestParam("categoryName")String categoryName){
        return categoryService.modCategory(categoryId,categoryName);
    }

    /**
     * 管理员获取全部分类接口
     * @param query 分页参数
     * @return 返回前端数据
     */
    @PostMapping("/getAllCategory")
    public Result getAllCategory(@RequestBody QueryPageParam query){
        return categoryService.getAllCategory(query);
    }

    /**
     * 管理员获取按名字全部分类接口
     * @param query 分页参数
     * @return 返回前端数据
     */
    @PostMapping("/getCategoryBycategoryName")
    public Result getCategoryByCategoryName(@RequestBody QueryPageParam query){
        return categoryService.getCategoryByCategoryName(query);
    }
}

