package band.zhaozhao.service.impl;

import band.zhaozhao.entity.HomeProduct;
import band.zhaozhao.mapper.HomeProductMapper;
import band.zhaozhao.service.HomeProductService;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class HomeProductServiceImpl extends ServiceImpl<HomeProductMapper, HomeProduct> implements HomeProductService {

    @Autowired
    private HomeProductMapper homeProductMapper;

    @Override
    public Result productCategories(Integer categoryId) {
        List<HomeProduct> homeProduct = null;
        try {
            //按照分类的ID来查看商品分类信息
            homeProduct = homeProductMapper.getProductCategories(categoryId);
            if (!homeProduct.isEmpty()) {
                return Result.error("查看分类商品成功", 200, homeProduct);
            }
        } catch (Exception e) {
            return Result.success("查看商品分类失败", 201, homeProduct);
        }

        return Result.success("无数据", 200, homeProduct);
    }
}

