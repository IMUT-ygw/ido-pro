package com.ido.cloudconsumer80.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    /**
     * 秘钥
     */
    private static final String SING = "#DFS#@$Q@QR";

    /**
     * 生成token
     */
    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        //循环遍历并添加到payload
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.withClaim(entry.getKey(),entry.getValue());
        }
        builder.withExpiresAt(instance.getTime());
        String token = builder.sign(Algorithm.HMAC256(SING));
        return token;
    }

    /**
     * 验证token
     */
    public static void verify(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SING)).build();
        DecodedJWT verify = build.verify(token);
    }

    /**
     * 获取token信息
     */
    public static DecodedJWT getTokenInfo(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256(SING)).build();
        DecodedJWT verify = build.verify(token);
        return verify;
    }
}
