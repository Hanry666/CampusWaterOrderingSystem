package band.zhaozhao.service;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.Category;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
public interface CategoryService extends IService<Category> {

    /**
     * 返回所有的分类
     * @return 信息
     */
    Result getAllCategories();

    /**
     * 添加商品分类
     * @param categoryName 商品分类名
     * @return 信息
     */
    Result addCategory(String categoryName);

    /**
     * 删除商品分类
     * @param categoryId 商品ID
     * @return 信息
     */
    Result deleteCategory(BigDecimal categoryId);

    /**
     * 修改分类接口
     * @param categoryId 分类ID
     * @param categoryName 分类名
     * @return 信息
     */
    Result modCategory(BigDecimal categoryId, String categoryName);

    /**
     * 得到所有的分类
     * @param query 分类信息
     * @return 信息
     */
    Result getAllCategory(QueryPageParam query);

    /**
     * 按名字搜索分类
     * @param query 分类信息
     * @return 信息
     */
    Result getCategoryByCategoryName(QueryPageParam query);
}
