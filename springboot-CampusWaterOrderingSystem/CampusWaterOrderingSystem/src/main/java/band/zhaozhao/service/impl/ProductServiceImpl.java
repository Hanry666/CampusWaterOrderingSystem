package band.zhaozhao.service.impl;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.Page;
import band.zhaozhao.entity.Product;
import band.zhaozhao.mapper.HomeProductMapper;
import band.zhaozhao.mapper.ProductMapper;
import band.zhaozhao.service.ProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private HomeProductMapper homeProductMapper;
    @Override
    public Result getProductDetailsById(Integer productId) {
        List product = null;
        try {
            //按照Id获取详情
            product = productMapper.getProductDetailsById(productId);
            if (!product.isEmpty()) {
                return Result.success("查看详情成功", 200, product);
            }
        } catch (Exception e) {
            return Result.error("查看详情失败", 201, product);
        }

        return Result.error("无数据", 200, product);
    }

    @Override
    public Result addProducts(BigDecimal categoryId,BigDecimal imageId, String productName, String productDescription, BigDecimal productPrice, BigDecimal productStock, String productStatus, String productDetails) {
        //添加商品
        Integer products = productMapper.addProdcuts(categoryId,imageId,productName,productDescription,productPrice,productStock,productStatus,productDetails);
        //获取商品的信息返回给前端
        Integer productId = productMapper.selectProductIdByProductName(productName);
        //把新添加的商品添加到首页展示的商品中
       Integer displayById = homeProductMapper.addDisplayById(categoryId,productId);
       if(products.equals(0) && displayById.equals(0)){
           return Result.error("添加物品失败",201,null);
       }
       //返回给前端商品信息
       List<Product> productList = productMapper.selectProductByProductName(productName);
       return Result.success("添加物品成功", 200,productList);
    }

    @Override
    public Result deleteProductsByProductId(BigDecimal productId) {
        //通过商品ID逻辑删除
       Integer product = productMapper.deleteProductsByProductId(productId);
       if(product.equals(0)){
           return Result.error("删除商品失败",201,null);
       }
       return Result.success("删除商品成功",200,null);
    }

    @Override
    public Result modProducts(BigDecimal productId, BigDecimal categoryId, BigDecimal imageId, String productName, String productDescription, BigDecimal productPrice, BigDecimal productStock, String productStatus, String productDetails) {
        //修改商品的信息
       Integer product = productMapper.modProductsById(productId,categoryId,imageId,productName,productDescription,productPrice,productStock,productStatus,productDetails);
       if (product.equals(0)) {
           return Result.error("修改商品失败", 201, null);
       }
       //修改后的商品信息返回给前端
        List<Product> productList = productMapper.selectProductByProductName(productName);
       return Result.success("修改商品成功",200,productList);
    }

    @Override
    public Result getAllProducts(QueryPageParam query) {
        //获取总条数
        final int totalRecords = productMapper.getAllProducts();
        //获取总页数通过前端的大小和总条数
        final int totalPages = (int) Math.ceil(( (double) productMapper.getAllProducts() /  query.getPageSize()));
        //设置分页数量
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //设置分页大小
        query.setPageSize(query.getPageNum() * query.getPageSize());
        List<Product> selectAllProducts = null;
        Page page = new Page();
        try {
            //按分页参数查找
            selectAllProducts = productMapper.selectAllProducts(query.getPageSize(), query.getPageNum());
            //添加到分页类中返回给前端
            page.setProductList(selectAllProducts);
            page.setTotalRecords(totalRecords);
            page.setTotalPages(totalPages);
            if (selectAllProducts != null) {
                return Result.success("查询成功", 200, page);
            }
        } catch (NullPointerException e) {
            return Result.error("查询失败", 201, page);
        }

        return Result.success("无数据", 200, page);
    }

    @Override
    public Result selectProductByProductName(QueryPageParam query) {
        //设置分页的数量
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //设置分页大小
        query.setPageSize(query.getPageNum() * query.getPageSize());
        //获取前端的商品名
        HashMap map = query.getParam();
        String productName = (String) map.get("productName");
        List<Product> productList = null;
        //设置名字的总条数
        final int totalRecords = productMapper.selectAllProductByName(productName);
        //设置名字的总页数
        final int totalPages = (int) Math.ceil(( (double) productMapper.selectAllProductByName(productName) /  query.getPageSize()));
        Page page = new Page();
        try {
            //按分页数据查找
            productList = productMapper.selectProductsByName(query.getPageSize(), query.getPageNum(), productName);
            //添加到分页类中返回给前端
            page.setProductList(productList);
            page.setTotalRecords(totalRecords);
            page.setTotalPages(totalPages);
            if (!productList.isEmpty()) {
                return Result.success("查询成功", 200, page);
            }
        } catch (NullPointerException n) {
            return Result.error("查询失败", 201, page);
        }
        return Result.error("无数据", 200, page);
    }
}
