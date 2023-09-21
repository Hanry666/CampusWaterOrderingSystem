package band.zhaozhao.controller;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.service.DeliveryService;
import band.zhaozhao.service.OrdersService;
import band.zhaozhao.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private OrdersService ordersService;

    /**
     * 配送员正在进行的订单
     * @param deliveryId 配送员ID
     * @return 返回前端数据
     */
    @GetMapping("/orders/active")
    public Result getDeliveryActiveOrders(@RequestParam("deliveryId")BigDecimal deliveryId){
       return ordersService.getDeliveryActiveOrders(deliveryId);
    }

    /**
     * 配送员历史的订单
     * @param deliveryId 配送员ID
     * @return 返回前端数据
     */
    @GetMapping("/orders/history")
    public Result getDeliveryHistoryOrders(@RequestParam("deliveryId")BigDecimal deliveryId){
        return ordersService.getDeliveryHistoryOrders(deliveryId);
    }

    /**
     * 配送员完成订单接口
     * @param orderId 订单ID
     * @return 返回前端数据
     */
    @PostMapping("/orders/complete")
    public Result completeOrders(@RequestParam("orderId") BigDecimal orderId){
        return ordersService.updateOrderStatusByOrderId(orderId);
    }

    /**
     * 配送员登录接口
     * @param deliveryPhone 配送员手机号
     * @param deliveryPassword 配送员密码
     * @return 返回前端数据
     */
    @PostMapping("/login")
    public Result login(@RequestParam("deliveryPhone") String deliveryPhone,
                        @RequestParam("deliveryPassword")String deliveryPassword){
        return deliveryService.login(deliveryPhone, deliveryPassword );
    }

    /**
     * 配送员注册接口
     * @param deliveryName 配送员姓名
     * @param deliveryPhone 配送员手机号
     * @param deliveryPassword 配送员密码
     * @param gender 配送员性别
     * @param role 配送员角色
     * @return 返回前端数据
     */
    @PostMapping("/register")
    public Result register(@RequestParam("deliveryName") String deliveryName,
                           @RequestParam("deliveryPhone") String deliveryPhone,
                           @RequestParam("deliveryPassword") String deliveryPassword,
                           @RequestParam("gender") String gender,
                           @RequestParam("role") String role) {
        return deliveryService.register(deliveryName, deliveryPhone, deliveryPassword,gender, role);
    }

    /**
     * 管理员修改配送员信息接口
     * @param deliveryId 配送员ID
     * @param deliveryName 配送员名字
     * @param deliveryPhone 配送员手机号
     * @param deliveryPassword 配送员密码
     * @param gender 配送员性别
     * @param balance 配送员余额
     * @param role 配送员角色
     * @return 返回前端数据
     */
    @PutMapping("/modDelivery")
    public Result modDelivery(@RequestParam("deliveryId") BigDecimal deliveryId,
                                @RequestParam("deliveryName") String deliveryName,
                              @RequestParam("deliveryPhone") String deliveryPhone,
                              @RequestParam("deliveryPassword") String deliveryPassword,
                              @RequestParam("gender") String gender,
                              @RequestParam("balance") BigDecimal balance,
                              @RequestParam("role") String role){
        return deliveryService.modDelivery(deliveryId,deliveryName,deliveryPhone,deliveryPassword,gender,balance,role);
    }

    /**
     * 配送员删除接口
     * @param deliveryId 配送员ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteDeliver")
    public Result deleteDeliver(@RequestParam("deliveryId") BigDecimal deliveryId){
        return deliveryService.deleteDeliverByDeliveryId(deliveryId);
    }

    /**
     * 获取所有配送员接口
     * @param query 分页参数
     * @return 返回前端数据
     */
    @PostMapping("/getAllDeliver")
    public Result getAllDeliver(@RequestBody QueryPageParam query){
        return deliveryService.getAllDeliver(query);
    }

    /**
     * 按名字获取所有配送员接口
     * @param query 分页参数
     * @return 返回前端数据
     */
    @PostMapping("/getAllDeliverByName")
    public Result getAllDeliverByName(@RequestBody QueryPageParam query){
        return deliveryService.getAllDeliverByName(query);
    }
}

