package com.ido.cloudconsumer80;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;


class CloudConsumer80ApplicationTests {

    @Test
    void contextLoads() {
        HashMap<String, Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,100);
        String token = JWT.create()
                .withHeader(map) //header
                .withClaim("username", "ygw") //payload
                .withClaim("age", 22) //payload
                .withExpiresAt(calendar.getTime()) //指定令牌过期时间
                .sign(Algorithm.HMAC256("token!q@w#e$r"));//签名秘钥

        System.out.println(token);
    }


    @Test
    public  void test(){
        //创建验证对象
        JWTVerifier require = JWT.require(Algorithm.HMAC256("token!q@w#e$r")).build();
        DecodedJWT verify = require.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MzQxOTEyNDksImFnZSI6MjIsInVzZXJuYW1lIjoieWd3In0.rKjrxAJlIsiKl5rfeo7ICnd5gIPJ1r8LrewcmIEYNfU");
        System.out.println(verify.getHeader());
        System.out.println(verify.getPayload());
        System.out.println(verify.getSignature());
        System.out.println(verify.getClaim("username").asString());
        System.out.println(verify.getClaim("age").asInt());
        System.out.println("过期时间：" + verify.getExpiresAt());


    }
}
