package band.zhaozhao.mapper;

import band.zhaozhao.entity.Address;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * 通过userId添加地址信息
     * @param userId 用户ID
     * @param campus 校园
     * @param building 楼号
     * @param houseNumber 房间
     * @return 信息
     */
    Integer addAddressByUserId(BigDecimal userId, String campus, String building, String houseNumber);

    /**
     * 通过userId寻找地址信息
     * @param userId 用户ID
     * @return 地址信息
     */
    List<Address> selectAllAddressByUserId(BigDecimal userId);

    /**
     * 根据信息来查找地址ID
     * @param userId 用户ID
     * @param campus 校园
     * @param building 楼号
     * @param houseNumber 房间
     * @return 地址ID
     */
    BigDecimal selectAddressIdByUserId(BigDecimal userId, String campus, String building, String houseNumber);

    /**
     * 修改地址信息
     * @param addressId 地址ID
     * @param campus 校园
     * @param building 楼号
     * @param houseNumber 房间
     * @return 修改信息
     */
    Integer modAddress(BigDecimal addressId, String campus, String building, String houseNumber);

    /**
     * 通过addressId获得地址信息
     * @param addressId 地址ID
     * @return 地址信息
     */
    List<Address> selectAddressByAddressId(BigDecimal addressId);

    /**
     * 根据addressId删除地址
     * @param addressId 地址Id
     * @return 信息
     */
    Integer deleteAddressByAddressId(BigDecimal addressId);
}
