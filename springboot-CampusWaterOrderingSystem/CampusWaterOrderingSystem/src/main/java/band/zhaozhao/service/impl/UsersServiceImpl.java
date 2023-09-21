package band.zhaozhao.service.impl;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.*;
import band.zhaozhao.mapper.OrdersMapper;
import band.zhaozhao.mapper.UsersMapper;
import band.zhaozhao.service.UsersService;
import band.zhaozhao.utils.JwtUtils;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Result payOrder(BigDecimal orderNumber, BigDecimal totalAmount) {
        //判断订单号是否已经下单
        if("已下单".equals(ordersMapper.selectOrderStatusByOrderNumber(orderNumber))) {
            //按订单号获取用户ID
            BigDecimal userId = ordersMapper.selectUserIdByOrderNmber(orderNumber);
            //获取用户余额
            BigDecimal balance = usersMapper.selectBalanceByUserId(userId);
            //计算是否够用
            int comparison = (balance.subtract(totalAmount)).compareTo(BigDecimal.ZERO);
            if (comparison < 0) {
                return Result.error("余额不足", 201, null);
            }
            //更新用户余额
            balance =balance.subtract(totalAmount);
            Integer usersPay = usersMapper.payOrder(userId, balance);
            if (usersPay == null) {
                return Result.error("支付失败", 201, null);
            }
            //更新用户状态
            String orderStatus = "已支付";
            Integer status = ordersMapper.updateOrderStatusByOrderNumber(orderNumber, orderStatus);
            if (status == null) {
                return Result.error("更新状态失败",201,null);
            }
            return Result.success("支付成功",200,null);
        }

        String status = ordersMapper.selectOrderStatusByOrderNumber(orderNumber);
        return Result.error("商品"+status,201,null);
    }

    @Override
    public Result login(String userPhone, String userPassword) {
        //是否存在手机号
        String selectedPhone = usersMapper.selectPhone(userPhone);
        try {
            //手机号不存在
            if(!selectedPhone.equals(userPhone)){
                return Result.error("手机号错误",201, selectedPhone);
            }
        }catch (NullPointerException n) {
            return Result.error("手机号还未注册", 201, selectedPhone);
        }
        //按手机号查看密码
        String selectPassword = usersMapper.selectPassword(userPhone);
        //判断是否相同
        if(!selectPassword.equals(userPassword)){
            return Result.error("密码错误", 201, selectPassword);
        }
        //把配送员的信息放入token的类中，并把角色和手机号放入token并放在请求头，用于验证
        Token token = new Token();
        token.setPhone(userPhone);
        token.setRole("个人用户");
        token.setId(usersMapper.selectUserIdByUserPhone1(userPhone));
        token.setName(usersMapper.selectUserNameByPhone(userPhone));
        Users users = new Users();
        users.setToken(JwtUtils.getToken(token));
        users.setUserId(usersMapper.selectUserIdByUserPhone(userPhone));
        users.setUserName(usersMapper.selectUserNameByPhone(userPhone));
        return Result.success("登录成功", 200,users);
    }

    @Override
    public Result register(String userName, String userPhone, String userPassword, String gender, String role) {
        Users users = new Users();
        try {
            //判断手机号是否存在
            if(userPhone.equals(usersMapper.selectPhone(userPhone))) {
                return Result.error("手机号已注册", 200, usersMapper.selectPhone(userPhone));
            }
            //将用户的信息存入数据库
            if(usersMapper.registerUser(userName,userPhone,userPassword,gender,role) > 0){
                //返回给前端
                BigDecimal phone = usersMapper.selectUserIdByUserPhone(userPhone);
                users.setUserId(phone);
                users.setUserName(userName);
                users.setUserPhone(userPhone);
                return Result.success("注册成功",200, users);
            }
        }catch (Exception e){
            return Result.error("注册失败",201, users);
        }
        return Result.error("没有填写用户信息",200, users);
    }

    @Override
    public Result userMod(BigDecimal userId, String userName, String userPhone, String userPassword, String gender, String role) {
       //用户修改，将用户信息存入数据库
        Integer user = usersMapper.userMod(userId, userName, userPhone, userPassword, gender, role);
       if (user.equals(0)) {
           return Result.error("更新失败",201,null);
       }
       //返回给前端信息
       List<Users> users = usersMapper.selectUsersByUserId(userId);
       return Result.success("更新成功",200,users);
    }

    @Override
    public Result mod(BigDecimal userId, String userName, String userPhone, String userPassword, String gender, BigDecimal balance, String role, BigDecimal priority) {
      //管理员修改
      Integer user = usersMapper.mod(userId,userName,userPhone,userPassword,gender,balance,role,priority);
      if (user.equals(0)) {
            return Result.error("更新失败",201,null);
      }
      List<Users> users = usersMapper.selectUsersByUserId(userId);
      return Result.success("更新成功",200,users);
    }

    @Override
    public Result deleteUserByUserId(BigDecimal userId) {
        //按照用户的ID删除用户
       Integer user = usersMapper.deleteUserByUserId(userId);
       if(user.equals(0)){
           return Result.error("删除用户失败",201,null);
       }
       return Result.success("删除用户成功",200,null);
    }

    @Override
    public Result getAllUsers(QueryPageParam query) {
        //查看所有的记录数
        final int totalRecords = usersMapper.selectAllUsers();
        //计算总页数
        final int totalPages = (int) Math.ceil(( (double) usersMapper.selectAllUsers() /  query.getPageSize()));
        //接收前端的分页信息
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //接收前端的分页信息
        query.setPageSize(query.getPageNum() * query.getPageSize());
        List selectAllUser = null;
        Page page = new Page();
        try {
            //按分页信息查看信息
            selectAllUser = usersMapper.selectAllUser(query.getPageSize(), query.getPageNum());
            //存入分页类中返回给前端
            page.setUsersList(selectAllUser);
            page.setTotalRecords(totalRecords);
            page.setTotalPages(totalPages);
            if (selectAllUser != null) {
                return Result.success("查询成功", 200, page);
            }
        } catch (Exception e) {
            return Result.error("查询失败", 201, page);
        }

        return Result.success("无数据", 200, page);
    }

    @Override
    public Result selectUsersByUserName(QueryPageParam query) {
        //接收前端的页数
        query.setPageNum(1 + ((query.getPageNum() - 1) * query.getPageSize()));
        //接收前端的大小
        query.setPageSize(query.getPageNum() * query.getPageSize());
        //获取前端传来的用户名
        HashMap map = query.getParam();
        String userName = (String) map.get("userName");
        //查看总记录数
        final int totalRecords = usersMapper.selectAllUsersByName(userName);
        //查看总页数
        final int totalPages = (int) Math.ceil(( (double) usersMapper.selectAllUsersByName(userName) /  query.getPageSize()));
        Page page = new Page();
        List<Users> usersList = null;
        try {
            //按分页信息查看信息
            usersList = usersMapper.selectByName(query.getPageSize(), query.getPageNum(), userName);
            //将信息放入分页类
            page.setUsersList(usersList);
            page.setTotalPages(totalPages);
            page.setTotalRecords(totalRecords);
            if (!usersList.isEmpty()) {
                return Result.success("查询成功", 200, page);
            }
        } catch (Exception n) {
            return Result.error("查询失败", 201, page);
        }
        return Result.error("无数据", 200, page);
    }
}
