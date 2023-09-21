package band.zhaozhao.service;

import band.zhaozhao.entity.Address;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
public interface AddressService extends IService<Address> {

    /**
     * 添加用户地址
     * @param userId 用户ID
     * @param campus 校园
     * @param building 楼号
     * @param houseNumber 房间
     * @return 信息
     */
    Result addAddressByUserId(BigDecimal userId, String campus, String building, String houseNumber);

    /**
     * 按用户ID找到所有地址
     * @param userId 用户ID
     * @return 信息
     */
    Result selectAllAddressByUserId(BigDecimal userId);

    /**
     * 查看所有的信息
     * @param userId 用户ID
     * @param shippingAddress 地址
     * @return 信息
     */
    BigDecimal selectAddressId(BigDecimal userId,List<Address> shippingAddress);

    /**
     * 修改地址信息
     * @param addressId 地址ID
     * @param campus 校园
     * @param building 楼号
     * @param houseNumber 房间
     * @return 信息
     */
    Result modAddress(BigDecimal addressId, String campus, String building, String houseNumber);

    /**
     * 按地址ID删除地址
     * @param addressId 地址ID
     * @return 信息
     */
    Result deleteAddressByAddressId(BigDecimal addressId);
}
