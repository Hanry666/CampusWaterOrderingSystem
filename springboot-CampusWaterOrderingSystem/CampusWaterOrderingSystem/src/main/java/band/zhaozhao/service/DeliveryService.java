package band.zhaozhao.service;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.Delivery;
import band.zhaozhao.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zzhaozhao
 * @since 2023-06-06
 */
public interface DeliveryService extends IService<Delivery> {

    /**
     * 登录实现
     * @param deliveryPhone 配送员电话
     * @param deliveryPassword 配送员密码
     * @return 信息
     */
    Result login(String deliveryPhone, String deliveryPassword);

    /**
     * 注册实现
     * @param deliveryName 配送员名字
     * @param deliveryPhone 配送员手机号
     * @param deliveryPassword 配送员密码
     * @param gender 性别
     * @param role 角色
     * @return 信息
     */
    Result register(String deliveryName, String deliveryPhone, String deliveryPassword, String gender, String role);

    /**
     * 管理员修改信息接口
     * @param deliveryId 配送员ID
     * @param deliveryName 配送员名字
     * @param deliveryPhone 配送员手机号
     * @param deliveryPassword 配送员密码
     * @param gender 性别
     * @param balance 余额
     * @param role 角色
     * @return 信息
     */
    Result modDelivery(BigDecimal deliveryId, String deliveryName, String deliveryPhone, String deliveryPassword, String gender, BigDecimal balance, String role);

    /**
     * 按配送员ID删除配送员
     * @param deliveryId 配送员ID
     * @return 信息
     */
    Result deleteDeliverByDeliveryId(BigDecimal deliveryId);

    /**
     * 按信息查看配送员
     * @param query 信息
     * @return 信息
     */
    Result getAllDeliver(QueryPageParam query);

    /**
     * 按名字查看配送员
     * @param query 信息
     * @return 信息
     */
    Result getAllDeliverByName(QueryPageParam query);
}
