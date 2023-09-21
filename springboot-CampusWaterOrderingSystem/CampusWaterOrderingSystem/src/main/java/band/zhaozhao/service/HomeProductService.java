package band.zhaozhao.service;

import band.zhaozhao.entity.HomeProduct;
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
public interface HomeProductService extends IService<HomeProduct> {


    /**
     * 获取分类信息
     * @param categoryId 分类ID
     * @return 信息
     */
    Result productCategories(Integer categoryId);

}
