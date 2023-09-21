package band.zhaozhao.utils;

import lombok.Data;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Zzhaozhao
 * @date 2023/5/28
 * @apiNote
 */
@Data
public class Result<T> {
    /**
     * msg：返回前端的信息
     */
    private String msg;

    /**
     * 返回前端的状态嘛
     */
    private Integer code;

    /**
     * 返回前端的数据
     */
    private T data;

    /**
     * 成功返回给前端的数据
     * @param msg 成功的信息
     * @param code 成功的状态码
     * @param data 成功要返回的数据
     */

    public static <T> Result<T> success(String msg, Integer code, T data){
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = code;
        result.data = data;
        return result;
    }
    /**
     * 失败返回给前端的数据
     * @param msg 失败的信息
     * @param code 失败的状态码
     * @param data 失败要返回的数据
     */
    public static <T> Result<T> error(String msg, Integer code, T data){
        Result<T> result = new Result<>();
        result.msg = msg;
        result.code = code;
        result.data = data;
        return result;
    }
}
