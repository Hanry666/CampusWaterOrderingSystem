package band.zhaozhao.utils;

import band.zhaozhao.entity.Delivery;
import band.zhaozhao.entity.Token;
import band.zhaozhao.entity.Users;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * @author Zzhaozhao
 * @date 2023/5/28
 * @apiNote
 */
@Slf4j
public class JwtUtils {
    private static final String SIGN = "!@#$%12345";

    /**
     * 生成token
     * header.payload.sign
     * @param
     *
     */
    public static String getToken(Token tokens){
        Calendar instance = Calendar.getInstance();
        //设置过期时间 七天过期
        instance.add(Calendar.DATE,100);
        JWTCreator.Builder builder = JWT.create();
        //payload
        builder.withClaim("phone" , tokens.getPhone())
                .withClaim("role" , tokens.getRole())
                .withClaim("id", tokens.getId())
                .withClaim("name",tokens.getName());
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    public static String getToken(Delivery delivery){
        Calendar instance = Calendar.getInstance();
        //设置过期时间 七天过期
        instance.add(Calendar.MINUTE,100);
        JWTCreator.Builder builder = JWT.create();
        //payload
        builder.withClaim("deliveryPhone" , delivery.getDeliveryPhone())
                .withClaim("role" , delivery.getRole())
                ;
        return builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 验证token合法性
     * @param token 传进token进行验证
     */
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
