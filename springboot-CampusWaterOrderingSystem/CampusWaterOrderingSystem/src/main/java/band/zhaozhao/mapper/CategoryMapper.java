package band.zhaozhao.mapper;

import band.zhaozhao.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 得到所有分类
     * @return 分类信息
     */
    List<Category> getAllCategories();

    /**
     * 添加分类
     * @param categoryName 分类名
     * @return 信息
     */
    Integer addCategory(String categoryName);

    /**
     * 通过分类名查看分类信息
     * @param categoryName 分类名
     * @return 分类信息
     */
    List<Category> selectCategoryByCategoryName(String categoryName);

    /**
     * 通过分类ID删除分类
     * @param categoryId 分类ID
     * @return 信息
     */
    Integer deleteCategoryByCategoryId(BigDecimal categoryId);

    /**
     * 修改分类接口
     * @param categoryId 分类ID
     * @param categoryName 分类名
     * @return 信息
     */
    Integer modCategoryByCategoryId(BigDecimal categoryId, String categoryName);

    /**
     * 分页查看分类信息
     * @param pageSize 页面大小
     * @param pageNum 页面数
     * @return 信息
     */
    List<Category> selectAllCategory(int pageSize, int pageNum);

    /**
     * 按名字分页查询分类信息
     * @param pageSize 页面大小
     * @param pageNum 页面数
     * @param categoryName 页面名
     * @return 分类信息
     */
    List<Category> selectCategoryByName(int pageSize, int pageNum, String categoryName);

    /**
     * 获得总条数
     * @return 条数
     */
    int selectAllCategories();

    /**
     * 查看名字的总条数
     * @param categoryName 分类名
     * @return 条数
     */
    int selectAllCategoriesByName(String categoryName);
}
