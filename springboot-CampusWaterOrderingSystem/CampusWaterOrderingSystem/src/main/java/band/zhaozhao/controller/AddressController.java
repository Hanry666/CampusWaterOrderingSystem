package band.zhaozhao.controller;

import band.zhaozhao.service.AddressService;
import band.zhaozhao.service.OrderProductService;
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
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderProductService orderProductService;

    /**
     * 根据用户Id查看用户所有保存的地址
     * @param userId 用户Id
     * @return 返回前端数据
     */
    @GetMapping("/getAllUserAddress")
    public Result getAllUserAddress(@RequestParam("userId")BigDecimal userId){
        return addressService.selectAllAddressByUserId(userId);
    }

    /**
     *  添加订单中的地址
     * @param addressId 地址ID
     * @param orderId 订单ID
     * @return 返回前端数据
     */
    @PostMapping("/addOrderAddressId")
    public Result addOrderAddressId(@RequestParam("addressId")BigDecimal addressId,
                                    @RequestParam("orderId")BigDecimal orderId){
        return orderProductService.addAddressByOrderId(addressId,orderId);
    }

    /**
     *  给用户添加地址
     * @param userId 用户ID
     * @param campus 校区
     * @param building 楼号
     * @param houseNumber 房间
     * @return 返回前端数据
     */
    @PostMapping("/addUserAddress")
    public Result addUserAddress(@RequestParam("userId") BigDecimal userId,
                                 @RequestParam("campus") String campus,
                                 @RequestParam("building") String building,
                                 @RequestParam("houseNumber") String houseNumber){
        return addressService.addAddressByUserId(userId,campus,building,houseNumber);
    }

    /**
     * 修改地址
     * @param addressId 地址ID
     * @param campus 校区
     * @param building 楼号
     * @param houseNumber 房间
     * @return 返回前端数据
     */
    @PostMapping("/modAddress")
    public Result modAddress(@RequestParam("addressId")BigDecimal addressId,
                             @RequestParam("campus")String campus,
                             @RequestParam("building") String building,
                             @RequestParam("houseNumber") String houseNumber){
        return addressService.modAddress(addressId,campus,building,houseNumber);
    }

    /**
     * 删除地址ID
     * @param addressId 地址ID
     * @return 返回前端数据
     */
    @DeleteMapping("/deleteAddress")
    public Result deleteAddress(@RequestParam("addressId")BigDecimal addressId){
        return addressService.deleteAddressByAddressId(addressId);
    }
}


