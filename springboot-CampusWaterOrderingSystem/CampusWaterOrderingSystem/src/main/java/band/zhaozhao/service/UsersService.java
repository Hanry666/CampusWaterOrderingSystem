package band.zhaozhao.service;

import band.zhaozhao.comment.QueryPageParam;
import band.zhaozhao.entity.OrderRequest;
import band.zhaozhao.entity.Users;
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
public interface UsersService extends IService<Users> {

    /**
     * 支付接口
     * @param orderNumber 订单号
     * @param totalAmount 总价格
     * @return 信息
     */
    Result payOrder(BigDecimal orderNumber, BigDecimal totalAmount);

    /**
     * 登录接口
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @return 信息
     */
    Result login(String userPhone, String userPassword);

    /**
     * 注册接口
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 性别
     * @param role 角色
     * @return 信息
     */
    Result register(String userName, String userPhone, String userPassword, String gender, String role);

    /**
     * 用户修改接口
     * @param userId 用户Id
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 用户性别
     * @param role 角色
     * @return 信息
     */
    Result userMod(BigDecimal userId,String userName, String userPhone, String userPassword, String gender, String role);

    /**
     * 管理员修改接口
     * @param userId 用户ID
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 用户性别
     * @param balance 余额
     * @param role 角色
     * @param priority 优先级
     * @return 信息
     */
    Result mod(BigDecimal userId, String userName, String userPhone, String userPassword, String gender, BigDecimal balance, String role, BigDecimal priority);

    /**
     * 按照用户ID删除接口
     * @param userId 用户ID
     * @return 信息
     */
    Result deleteUserByUserId(BigDecimal userId);

    /**
     * 获取所有用户接口
     * @param query 分页信息
     * @return 信息
     */
    Result getAllUsers(QueryPageParam query);

    /**
     * 获取所有名字用户的接口
     * @param query 分页信息
     * @return 信息
     */
    Result selectUsersByUserName(QueryPageParam query);
}
