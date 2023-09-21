package band.zhaozhao.mapper;

import band.zhaozhao.entity.Delivery;
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
public interface DeliveryMapper extends BaseMapper<Delivery> {

    /**
     * 通过手机号查询
     * @param deliveryPhone 配送员手机号
     * @return 手机号
     */
    String selectPhone(String deliveryPhone);

    /**
     * 通过手机号查看密码
     * @param deliveryPhone 配送员手机号
     * @return 密码
     */
    String selectPassword(String deliveryPhone);

    /**
     * 添加配送员
     * @param deliveryName 配送员姓名
     * @param deliveryPhone 配送员
     * @param deliveryPassword 配送员密码
     * @param gender 性别
     * @param role 角色
     * @return 信息
     */
    Integer registerDelivery(String deliveryName, String deliveryPhone, String deliveryPassword, String gender, String role);

    /**
     * 通过配送员电话查找配送员Id
     * @param deliveryPhone 配送员手机号
     * @return 信息
     */
    BigDecimal selectDeliveryIdByDeliveryPhone(String deliveryPhone);

    /**
     * 通过配送员Id查看余额
     * @param deliveryId 配送员ID
     * @return 余额
     */
    BigDecimal selectBalanceByDeliveryId(BigDecimal deliveryId);

    /**
     * 跟新配送员余额
     * @param deliveryId 配送员Id
     * @param newBalance 余额
     * @return 信息
     */
    Integer updateBalanceByDeliveryId(BigDecimal deliveryId, BigDecimal newBalance);

    /**
     * 通过配送员Id修改信息
     * @param deliveryId 配送员ID
     * @param deliveryName 配送员姓名
     * @param deliveryPhone 配送员电话
     * @param deliveryPassword 配送员密码
     * @param gender 性别
     * @param balance 余额
     * @param role 角色
     * @return 信息
     */
    Integer modDeliveryByDeliveryId(BigDecimal deliveryId, String deliveryName, String deliveryPhone, String deliveryPassword, String gender, BigDecimal balance, String role);

    /**
     * 通过配送员Id 查看信息
     * @param deliveryId 配送员Id
     * @return 配送员信息
     */
    List<Delivery> selectDeliveryById(BigDecimal deliveryId);

    /**
     * 删除配送员
     * @param deliveryId 配送员Id
     * @return 信息
     */
    Integer deleteDeliverByDeliveryId(BigDecimal deliveryId);

    /**
     * 查询所有配送员Id
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @return 配送员信息
     */
    List<Delivery> selectAllDelivery(int pageSize, int pageNum);

    /**
     * 按名字查询配送员信息
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @param deliveryName 配送员名字
     * @return 配送员信息
     */
    List<Delivery> selectByName(int pageSize, int pageNum, String deliveryName);

    /**
     * 按配送员电话查询配送员名
     * @param deliveryPhone 配送员电话
     * @return 名字
     */
    String selectNameByPhone(String deliveryPhone);

    /**
     * 按电话搜索配送员ID
     * @param deliveryPhone 配送员电话
     * @return 配送员ID
     */
    Integer selectDeliveryIdByDeliveryPhone1(String deliveryPhone);

    /**
     * 配送员总条数
     * @return 条数
     */
    int selectAllDeliveries();

    /**
     * 按名字的总条数
     * @param deliveryName 配送员名字
     * @return 条数
     */
    int selectAllDeliveriesByName(String deliveryName);
}
