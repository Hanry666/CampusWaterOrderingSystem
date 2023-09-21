package band.zhaozhao.controller;

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
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 抢单界面
     * @return 返回前端数据
     */
    @GetMapping("/delivery/available")
    public Result getAllAvailableOrders(){
        return ordersService.getAllAvailableOrders();
    }

    /**
     * 抢单功能接口
     * @param deliveryId 配送员Id
     * @param orderNumber 订单号
     * @return 返回前端数据
     */
    @PostMapping("/delivery/claim")
    public Result claimOrders(@RequestParam("deliveryId")BigDecimal deliveryId,
                              @RequestParam("orderNumber") BigDecimal orderNumber){
        return ordersService.claimOrders(deliveryId, orderNumber);

    }
}

