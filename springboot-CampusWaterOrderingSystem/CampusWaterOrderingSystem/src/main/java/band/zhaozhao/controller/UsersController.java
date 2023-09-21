package band.zhaozhao.controller;


import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.*;
import band.zhaozhao.service.*;
import band.zhaozhao.utils.JwtUtils;
import band.zhaozhao.utils.Result;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.extension.api.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@RestController
@Slf4j
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartProductService cartProductService;

    /**
     * 添加订单接口
     * @param orderRequest 订单参数
     * @return 返回前端数
     */
    @PostMapping("/orders")
    public Result addOrders(@RequestBody OrderRequest orderRequest){
        //接收前端传来的订单信息
        List<OrderProduct> items = orderRequest.getItems();
        //接收用户Id
        BigDecimal userId = orderRequest.getUserId();
        //循环得到订单参数
        for(OrderProduct item: items){
            BigDecimal productId = item.getProductId();
            String datetimeStr = String.valueOf(item.getOrderDate());
            //接收前端传来的时间
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            LocalDateTime orderDate = LocalDateTime.parse(datetimeStr, formatter);
            BigDecimal productQuantity = item.getProductQuantity();
            BigDecimal addressId = item.getAddressId();
            //转到ordersService里添加订单
            return ordersService.addOrders(userId, productId, orderDate, productQuantity,addressId);

        }
        return Result.success("成功",200,null);
    }

    /**
     * 支付接口
     * @param orderNumber 订单号
     * @param totalAmount 总金额
     * @return 返回前端数据
     */
    @GetMapping("/payOrders")
    public Result payOrders(@RequestParam("orderNumber") BigDecimal orderNumber,
                            @RequestParam("totalAmount") BigDecimal totalAmount){
        return usersService.payOrder(orderNumber,totalAmount);
    }

    /**
     * 用户正在进行的订单
     * @param userId 用户的ID
     * @return 返回前端数据
     */
    @GetMapping("/orders/active")
    public Result getUserActiveOrder(@RequestParam("userId") BigDecimal userId){
        return ordersService.getUserActiveOrder(userId);
    }

    /**
     * 用户历史订单
     * @param userId 用户ID
     * @return 返回前端数据
     */
    @GetMapping("/orders/history")
    public Result getUserHistoryOrder(@RequestParam("userId") BigDecimal userId){
        return ordersService.getUserHistoryOrder(userId);
    }

    /**
     * 用户确认收货
     * @param orderId 订单ID
     * @return 返回前端数据
     */
    @GetMapping("/orders/confirm")
    public Result confirmOrder(@RequestParam("orderId") BigDecimal orderId){
        return ordersService.updateOrderStatusByOrderId(orderId);
    }

    /**
     * 用户未收到货接口
     * @param orderId 订单Id
     * @return 返回前端数据
     */
    @GetMapping("/orders/notReceived")
    public Result notReceived(@RequestParam("orderId") BigDecimal orderId){
        return ordersService.updateOrderNotReceivedByOrderId(orderId);
    }

    /**
     * 用户登录接口
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @return 返回前端数据
     */
    @PostMapping("/login")
    public Result login(@RequestParam("userPhone") String userPhone,
                        @RequestParam("userPassword") String userPassword){
        return usersService.login(userPhone,userPassword);
    }

    /**
     * 验证用户Token接口
     * @param token 传进token
     * @return 返回token状态、和用户手机号和角色
     */
    @GetMapping("/verifyToken")
    public Result verifyToken(@RequestParam("token") String token){
        //调用jwt验证方法，接收传进来的token得到token中的信息
        DecodedJWT verify = JwtUtils.verify(token);
        String phone = String.valueOf(verify.getClaim("phone")).replaceAll("\\\"","");
        String role = String.valueOf(verify.getClaim("role")).replaceAll("\\\"","");
        String id = String.valueOf(verify.getClaim("id")).replaceAll("\\\"","");
        String name = String.valueOf(verify.getClaim("name")).replaceAll("\\\"","");
        Token token1 = new Token();
        token1.setPhone(phone);
        token1.setRole(role);
        token1.setId(Integer.valueOf(id));
        token1.setName(name);
        return Result.success("token验证成功",200,token1);
    }

    /**
     * 用户注册接口
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 性别
     * @param role 角色
     * @return 返回前端数据
     */
    @PostMapping("/register")
    public Result register(@RequestParam("userName") String userName,
                           @RequestParam("userPhone") String userPhone,
                           @RequestParam("userPassword") String userPassword,
                           @RequestParam("gender") String gender,
                           @RequestParam("role") String role) {
      return usersService.register(userName, userPhone, userPassword,gender, role);
    }

    /**
     * 用户更改信息接口
     * @param userId 用户ID
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 性别
     * @param role 角色
     * @return 返回前端数据
     */
    @PutMapping("/userMod")
    public Result userMod(@RequestParam("userId") BigDecimal userId,
                        @RequestParam("userName") String userName,
                      @RequestParam("userPhone") String userPhone,
                      @RequestParam("userPassword") String userPassword,
                      @RequestParam("gender") String gender,
                      @RequestParam("role") String role){
       return usersService.userMod(userId, userName, userPhone, userPassword, gender, role);
    }

    /**
     * 管理员修改用户接口
     * @param userId 用户ID
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 性别
     * @param balance 余额
     * @param role 角色
     * @param priority 优先级
     * @return 返回前端数据
     */
    @PutMapping("/mod")
    public Result mod(@RequestParam("userId") BigDecimal userId,
                      @RequestParam("userName") String userName,
                      @RequestParam("userPhone") String userPhone,
                      @RequestParam("userPassword") String userPassword,
                      @RequestParam("gender") String gender,
                      @RequestParam("balance") BigDecimal balance,
                      @RequestParam("role") String role,
                      @RequestParam("priority") BigDecimal priority){
        return usersService.mod(userId,userName,userPhone,userPassword,gender,balance,role,priority);
    }

    /**
     * 用户查看购物车接口
     * @param userId 用户ID
     * @return 返回前端数据
     */
    @GetMapping("/shoppingCart")
    public Result showUsersShoppingCart(@RequestParam("userId") BigDecimal userId){
        return cartProductService.showUserCartByUserId(userId);
    }

    /**
     * 删除用户接口
     * @param userId 用户ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam("userId") BigDecimal userId){
        return usersService.deleteUserByUserId(userId);
    }

    /**
     * 查询所有用户接口
     * @param query 分页参数
     * @return 返回前端数据
     */
    @PostMapping("/getAllUsers")
    public Result getAllUsers(@RequestBody QueryPageParam query){
        return usersService.getAllUsers(query);
    }

    /**
     * 按名字查询用户接口
     * @param query 分页参数
     * @return 返回前端数据
     */
    @PostMapping("/selectUsersByuserName")
    public Result selectUsersByUserName(@RequestBody QueryPageParam query){
        return usersService.selectUsersByUserName(query);
    }
}

