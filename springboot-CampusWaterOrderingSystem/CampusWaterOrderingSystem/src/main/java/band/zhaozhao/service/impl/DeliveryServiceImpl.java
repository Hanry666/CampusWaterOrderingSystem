package band.zhaozhao.service.impl;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.Delivery;
import band.zhaozhao.entity.Page;
import band.zhaozhao.entity.Token;
import band.zhaozhao.mapper.DeliveryMapper;
import band.zhaozhao.service.DeliveryService;
import band.zhaozhao.utils.JwtUtils;
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
public class DeliveryServiceImpl extends ServiceImpl<DeliveryMapper, Delivery> implements DeliveryService {

    @Autowired
    private DeliveryMapper deliveryMapper;


    @Override
    public Result login(String deliveryPhone, String deliveryPassword) {
        //查看传来的配送员手机号是否在数据库中有相应的数据
        String selectedPhone = deliveryMapper.selectPhone(deliveryPhone);
        try {
            //不存在
            if(!selectedPhone.equals(deliveryPhone)){
                return Result.error("手机号错误",200, null);
            }
        }catch (Exception n) {
            return Result.error("登录失败", 200, null);
        }
        //查看传来的配送员手机号是否在数据库中密码
        String selectPassword = deliveryMapper.selectPassword(deliveryPhone);
        //不一致
        if(!selectPassword.equals(deliveryPassword)){
            return Result.error("密码错误", 201, null);
        }
        //把配送员的信息放入token的类中，并把角色和手机号放入token并放在请求头，用于验证
        Token token = new Token();
        token.setPhone(deliveryPhone);
        token.setRole("配送员");
        token.setId(deliveryMapper.selectDeliveryIdByDeliveryPhone1(deliveryPhone));
        token.setName(deliveryMapper.selectNameByPhone(deliveryPhone));
        Delivery delivery = new Delivery();
        delivery.setDeliveryId(deliveryMapper.selectDeliveryIdByDeliveryPhone(deliveryPhone));
        delivery.setDeliveryName(deliveryMapper.selectNameByPhone(deliveryPhone));
        delivery.setToken(JwtUtils.getToken(token));
        return Result.success("登录成功", 200, delivery);
    }

    @Override
    public Result register(String deliveryName, String deliveryPhone, String deliveryPassword, String gender, String role) {
        Delivery delivery = new Delivery();
        try {
            //查看配送员手机号是否在数据库中有没有相应的手机号
            if(deliveryPhone.equals(deliveryMapper.selectPhone(deliveryPhone))) {
                //有
                return Result.error("手机号已注册", 200, delivery);
            }
            //没有，那么久把数据放在数据库中，并返回给前端
            if(deliveryMapper.registerDelivery(deliveryName,deliveryPhone,deliveryPassword,gender,role) > 0){
                BigDecimal phone = deliveryMapper.selectDeliveryIdByDeliveryPhone(deliveryPhone);
                delivery.setDeliveryId(phone);
                delivery.setDeliveryName(deliveryName);
                delivery.setDeliveryPhone(deliveryPhone);
                return Result.success("注册成功",200, delivery);
            }
        }catch (NullPointerException e){
            return Result.error("没有填写用户信息",200, delivery);
        }
        return Result.error("注册失败",201, delivery);
    }

    @Override
    public Result modDelivery(BigDecimal deliveryId, String deliveryName, String deliveryPhone, String deliveryPassword, String gender, BigDecimal balance, String role) {
        //修改配送员的信息
        Integer delivery = deliveryMapper.modDeliveryByDeliveryId(deliveryId,deliveryName,deliveryPhone,deliveryPassword,gender,balance,role);
       if (delivery.equals(0)){
           return Result.error("修改信息失败", 201,null);
       }
       //修改后的数据，返回给前端
       List<Delivery> deliveryList = deliveryMapper.selectDeliveryById(deliveryId);
       return Result.success("修改信息成功",200,deliveryList);
    }

    @Override
    public Result deleteDeliverByDeliveryId(BigDecimal deliveryId) {
        //按照配送员Id删除配送员
       Integer delivery = deliveryMapper.deleteDeliverByDeliveryId(deliveryId);
       if(delivery.equals(0)){
           return Result.error("删除配送员失败",201,null);
       }
       return Result.success("删除配送员成功",200,null);
    }

    @Override
    public Result getAllDeliver(QueryPageParam query) {
        //计算分页的总条数
        final int totalRecords = deliveryMapper.selectAllDeliveries();
        //计算分页的总页数
        final int totalPages = (int) Math.ceil(( (double) deliveryMapper.selectAllDeliveries() /  query.getPageSize()));
        //设置分页的页数给数据库
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //设置分页的大小给数据库
        query.setPageSize(query.getPageNum() * query.getPageSize());
        List<Delivery> selectAllDelivery = null;
        Page page = new Page();
        try {
            //按分页的数据搜索
            selectAllDelivery = deliveryMapper.selectAllDelivery(query.getPageSize(), query.getPageNum());
            //添加到分页类中返回给前端
            page.setDeliveryList(selectAllDelivery);
            page.setTotalRecords(totalRecords);
            page.setTotalPages(totalPages);
            if (!selectAllDelivery.isEmpty()) {
                return Result.success("查询成功", 200, page);
            }
        } catch (Exception e) {
            return Result.success("查询失败", 201, page);
        }

        return Result.error("无数据", 200, page);
    }

    @Override
    public Result getAllDeliverByName(QueryPageParam query) {
        //设置分页的页数给数据库
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //设置分页的大小给数据库
        query.setPageSize(query.getPageNum() * query.getPageSize());
        //将前端传来的配送员名字来查询
        HashMap map = query.getParam();
        String deliveryName = (String) map.get("deliveryName");
        //按名字的搜索总条数
        final int totalRecords = deliveryMapper.selectAllDeliveriesByName(deliveryName);
        //总页数
        final int totalPages = (int) Math.ceil(( (double) deliveryMapper.selectAllDeliveriesByName(deliveryName) /  query.getPageSize()));
        List<Delivery> deliveryList = null;
        Page page = new Page();
        try {
            //按分页的参数来查找
            deliveryList = deliveryMapper.selectByName(query.getPageSize(), query.getPageNum(), deliveryName);
            //返回给前端
            page.setDeliveryList(deliveryList);
            page.setTotalRecords(totalRecords);
            page.setTotalPages(totalPages);
            if (!deliveryList.isEmpty()) {
                return Result.success("查询成功", 200, page);
            }
        } catch (Exception n) {
            return Result.error("查询失败", 201, page);
        }

        return Result.error("无数据", 200, page);
    }
}
