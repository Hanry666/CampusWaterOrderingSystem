package band.zhaozhao.mapper;

import band.zhaozhao.entity.Users;
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
public interface UsersMapper extends BaseMapper<Users> {

    /**
     * 支付订单
     * @param userId 用户Id
     * @param balance 余额
     * @return 信息
     */
    Integer payOrder(BigDecimal userId, BigDecimal balance);

    /**
     * 按用户ID查看余额
     * @param userId 用户ID
     * @return 余额
     */
    BigDecimal selectBalanceByUserId(BigDecimal userId);

    /**
     * 按手机号搜索
     * @param userPhone 用户手机号
     * @return 信息
     */
    String selectPhone(String userPhone);

    /**
     * 按照用户手机号查看密码
     * @param userPhone 用户手机号
     * @return 密码
     */
    String selectPassword(String userPhone);

    /**
     * 注册
     * @param userName 用户名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 性别
     * @param role 角色
     * @return 信息
     */
    Integer registerUser(String userName, String userPhone, String userPassword, String gender, String role);

    /**
     * 按电话搜索用户ID
     * @param userPhone 用户手机号
     * @return 用户ID
     */
    BigDecimal selectUserIdByUserPhone(String userPhone);

    /**
     * 修改用户
     * @param userId 用户ID
     * @param userName 用户姓名
     * @param userPhone 用户手机号
     * @param userPassword 用户密码
     * @param gender 性别
     * @param role 角色
     * @return 信息
     */
    Integer userMod(BigDecimal userId, String userName, String userPhone, String userPassword, String gender, String role);

    /**
     * 按用户Id搜索用户信息
     * @param userId 用户Id
     * @return 用户信息
     */
    List<Users> selectUsersByUserId(BigDecimal userId);

    /**
     * 修改用户
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
    Integer mod(BigDecimal userId, String userName, String userPhone, String userPassword, String gender, BigDecimal balance, String role, BigDecimal priority);

    /**
     * 按用户Id删除
     * @param userId 用户ID
     * @return 信息
     */
    Integer deleteUserByUserId(BigDecimal userId);

    /**
     * 分页查询用户
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @return 信息
     */
    List<Users> selectAllUser(int pageSize, int pageNum);

    /**
     * 按名字分页查询用户
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @param userName 用户名
     * @return 信息
     */
    List<Users> selectByName(int pageSize, int pageNum, String userName);

    /**
     * 按手机号查询用户名
     * @param userPhone 用户电话
     * @return 用户名
     */
    String selectUserNameByPhone(String userPhone);

    /**
     * 按用户电话查询用户ID
     * @param userPhone 用户手机号
     * @return 用户ID
     */
    Integer selectUserIdByUserPhone1(String userPhone);

    /**
     * 用户所有条数
     * @return 条数
     */
    int selectAllUsers();

    /**
     * 按名字查看用户条数
     * @param userName 用户名
     * @return 条数
     */
    int selectAllUsersByName(String userName);
}
