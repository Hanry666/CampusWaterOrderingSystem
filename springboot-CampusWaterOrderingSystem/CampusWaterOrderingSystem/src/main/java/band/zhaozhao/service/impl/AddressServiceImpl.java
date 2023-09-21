package band.zhaozhao.service.impl;

import band.zhaozhao.entity.Address;
import band.zhaozhao.mapper.AddressMapper;
import band.zhaozhao.service.AddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public Result addAddressByUserId(BigDecimal userId, String campus, String building, String houseNumber) {
        Integer integer = addressMapper.addAddressByUserId(userId, campus, building, houseNumber);
        //添加用户地址
        BigDecimal addressId = addressMapper.selectAddressIdByUserId(userId,campus,building,houseNumber);
        if (integer == 0) {
            return Result.error("地址添加失败",201,null);
        }
        return Result.success("地址添加成功",200,addressId);

    }

    @Override
    public Result<List<Address>> selectAllAddressByUserId(BigDecimal userId) {
        List<Address> addresses = null;
        try {
            //通过传来的用户Id来查看地址
            addresses = addressMapper.selectAllAddressByUserId(userId);
            //判断传来的数据
            if (!addresses.isEmpty()) {
                return Result.success("用户所有的地址", 200, addresses);
            }
        } catch (Exception e) {
            return Result.success("查看用户所有的地址失败", 201, addresses);
        }

        return Result.error("用户无地址", 200, addresses);
    }

    @Override
    public BigDecimal selectAddressId(BigDecimal userId, List<Address> shippingAddress) {
        //取出传进来订单信息存入数据库
        for (Address address: shippingAddress) {
        return addressMapper.selectAddressIdByUserId(userId,address.getCampus(),address.getBuilding(),address.getHouseNumber());
        }
        return null;
    }

    @Override
    public Result modAddress(BigDecimal addressId, String campus, String building, String houseNumber) {
        //传进来的数据存入数据库
       Integer address = addressMapper.modAddress(addressId,campus,building,houseNumber);
       //判断数否存成功
       if(address.equals(0)){
           return Result.error("修改地址失败",201,null);
       }
       //成功就返回地址信息
       List<Address> addressList = addressMapper.selectAddressByAddressId(addressId);
       return Result.success("修改地址成功",200,addressList);
    }

    @Override
    public Result deleteAddressByAddressId(BigDecimal addressId) {
        //删除地址信息
       Integer address = addressMapper.deleteAddressByAddressId(addressId);
       if(address.equals(0)){
           return Result.error("删除地址失败",201,null);
       }
       return Result.success("删除地址成功",200,null);
    }

}
