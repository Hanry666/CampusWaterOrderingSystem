package band.zhaozhao.service.impl;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.Category;
import band.zhaozhao.entity.Page;
import band.zhaozhao.mapper.CategoryMapper;
import band.zhaozhao.service.CategoryService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Result getAllCategories() {
        List categories = null;
        try {
            //查看所有的分类商品
            categories = categoryMapper.getAllCategories();
            if (!categories.isEmpty()) {
                return Result.error("查询分类成功", 200, categories);
            }
        } catch (Exception e) {
            return Result.error("查看分类失败 ", 201, categories);
        }
            return Result.error("无分类", 201, categories);
    }

    @Override
    public Result addCategory(String categoryName) {
        //通过传来的分类名添加到数据库
       Integer category = categoryMapper.addCategory(categoryName);
       if(category.equals(0)){
           return Result.error("增加分类失败",201,null);
       }
       //加入成功后，就用分类名字查看分类的信息并返回给前端
       List<Category> categoryList = categoryMapper.selectCategoryByCategoryName(categoryName);
       return Result.success("增加分类成功",200,categoryList);
    }

    @Override
    public Result deleteCategory(BigDecimal categoryId) {
        //通过商品的ID来删除种类信息
       Integer category = categoryMapper.deleteCategoryByCategoryId(categoryId);
       if(category.equals(0)){
           return Result.error("删除物品失败",201,null);
       }
       return Result.success("删除物品成功",200,null);
    }

    @Override
    public Result modCategory(BigDecimal categoryId, String categoryName) {
        //修改分类的信息
       Integer category = categoryMapper.modCategoryByCategoryId(categoryId,categoryName);
       if (category.equals(0)){
           return Result.error("修改失败",201,null);
       }
       //返回给前端新的分类信息
        List<Category> categoryList = categoryMapper.selectCategoryByCategoryName(categoryName);
        return Result.success("修改成功",200,categoryList);
    }

    @Override
    public Result getAllCategory(QueryPageParam query) {
        //定义分页的总条数通过查询用户的数量
        final int totalRecords = categoryMapper.selectAllCategories();
        //定义分页的总页数，通过用户数量和前端页面的大小来获取总页数
        final int totalPages = (int) Math.ceil(( (double) categoryMapper.selectAllCategories() /  query.getPageSize()));
        //设置分页数
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //设置分页大小
        query.setPageSize(query.getPageNum() * query.getPageSize());
        List<Category> selectAllCategory = null;
        Page page = new Page();
        try {
            //将分页信息传给数据库来检索
            selectAllCategory = categoryMapper.selectAllCategory(query.getPageSize(), query.getPageNum());
            //返回的数据存到分页类中返回给前端
            page.setCategoryList(selectAllCategory);
            page.setTotalPages(totalPages);
            page.setTotalRecords(totalRecords);
            if (!selectAllCategory.isEmpty()) {
                return Result.success("查询成功", 200, page);
            }
        } catch (Exception e) {
            return Result.success("查询失败", 201, page);
        }
        return Result.error("无数据", 200, page);

    }

    @Override
    public Result getCategoryByCategoryName(QueryPageParam query) {
        //设置分页数
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //设置分页大小
        query.setPageSize(query.getPageNum() * query.getPageSize());
        //获取前端返回的分类名
        HashMap map = query.getParam();
        String categoryName = (String) map.get("categoryName");
        //通过分类名查看商品分类的总条数
        final int totalRecords = categoryMapper.selectAllCategoriesByName(categoryName);
        //通过分类名查看商品分类的总页
        final int totalPages = (int) Math.ceil(( (double) categoryMapper.selectAllCategoriesByName(categoryName) /  query.getPageSize()));
        List<Category> categoryList = null;
        Page page = new Page();
        try {
            //将分页信息传给数据库来检索
            categoryList = categoryMapper.selectCategoryByName(query.getPageSize(), query.getPageNum(), categoryName);
            //返回的数据存到分页类中返回给前端
            page.setCategoryList(categoryList);
            page.setTotalPages(totalPages);
            page.setTotalRecords(totalRecords);
            if (!categoryList.isEmpty()) {
                return Result.success("查询成功", 200, page);
            }
        } catch (Exception n) {
            return Result.error("查询失败", 201, page);
        }
        return Result.error("无数据", 200, page);
    }
}
