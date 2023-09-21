package band.zhaozhao.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaozhao
 * @since 2023-05-27
 */
@Getter
@Setter
@TableName("USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId("ID")
    private Long id;

    /**
     * 用户姓名
     */
    @TableField("USERNAME")
    private String username;

    /**
     * 用户性别
     */
    @TableField("GENDER")
    private String gender;

    /**
     * 用户地址
     */
    @TableField("ADDRESS")
    private String address;

    /**
     * 用户手机号
     */
    @TableField("PHONE")
    private Long phone;

    /**
     * 用户优先级
     */
    @TableField("LEVEL")
    private Long level;

    /**
     * 用户角色：管理员、用户、工人
     */
    @TableField("ROLE")
    private Long role;


}
