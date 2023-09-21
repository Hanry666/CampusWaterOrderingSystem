package band.zhaozhao.service.impl;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.HomePageProduct;
import band.zhaozhao.mapper.HomePageProductMapper;
import band.zhaozhao.service.HomePageProductService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
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
public class HomePageProductServiceImpl extends ServiceImpl<HomePageProductMapper, HomePageProduct> implements HomePageProductService {

    @Autowired
    public HomePageProductMapper homePageProductMapper;

    @Override
    public Result selectAllProduct() {
        List<HomePageProduct> homePageProducts = null;
        try {
            //数据库查找所有商品
            homePageProducts = homePageProductMapper.selectAllProduct();
            if (homePageProducts.isEmpty()) {
                return Result.success("滚动商品查询成功", 200, homePageProducts);
            }
        } catch (Exception e) {
            return Result.error("滚动商品查询失败", 201, homePageProducts);
        }
        return Result.success("无数据", 200, homePageProducts);

    }

    @Override
    public Result addHomePageProduct(BigDecimal productId, String imageUrl) {
        //添加首页的商品
        Integer homePage = homePageProductMapper.addHomePageProduct(productId,imageUrl);
        if(homePage.equals(0)){
            return Result.error("添加商品失败",200,null);
        }
        //添加商品的信息返回给前端
        List<HomePageProduct> homePageProductList =homePageProductMapper.selectIdByProductId(productId);
        return Result.success("商品添加成功",200,homePageProductList);
    }

    @Override
    public Result deleteHomePageProduct(BigDecimal carouselId) {
        //按照分类的ID删除
        Integer homePage = homePageProductMapper.deleteHomePageProductByCarouselId(carouselId);
        if (homePage.equals(0)){
            return Result.error("删除失败",201,null);
        }
        return Result.success("删除成功",200,null);
    }

    @Override
    public Result modHomePageProduct(BigDecimal carouselId, BigDecimal productId, String imageUrl) {
        //修改首页商品的信息
       Integer homePage = homePageProductMapper.modHomePageProduct(carouselId,productId,imageUrl);
       if(homePage.equals(0)){
           return Result.error("修改失败",201,null);
       }
       //修改后的商品信息返回给前端
       List<HomePageProduct> homePageProductList = homePageProductMapper.selectIdByCarouseId(carouselId);
       return Result.success("修改成功",200,homePageProductList);
    }

    @Override
    public Result getAllHomePageProduct(QueryPageParam query) {
        //获取前端的页数
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //获取前端的大小
        query.setPageSize(query.getPageNum() * query.getPageSize());
        List<HomePageProduct> selectAllHomePageProducts = null;
        try {
            //按照分页的数据给数据库搜索
            selectAllHomePageProducts = homePageProductMapper.selectAllHomePageProducts(query.getPageSize(), query.getPageNum());
            if (!selectAllHomePageProducts.isEmpty()) {
                return Result.success("查询成功", 200, selectAllHomePageProducts);
            }
        } catch (NullPointerException e) {
            return Result.success("查询失败", 201, selectAllHomePageProducts);
        }

        return Result.error("无数据", 200, selectAllHomePageProducts);
    }

}
