package com.ido.cloudconsumer80.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ido.cloudconsumer80.utils.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JWTInterceptor implements HandlerInterceptor {

    /**
     * 登录拦截器（建议把token放在header中）
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        HashMap<String, Object> map = new HashMap<>();
        try {
            JWTUtils.getTokenInfo(token);
            return true;
        } catch (SignatureVerificationException e) { //签名不一致异常
            e.printStackTrace();
            map.put("msg","签名不一致！");
        }catch (TokenExpiredException e){ //token过期异常
            e.printStackTrace();
            map.put("msg","token过期！");
        }catch (AlgorithmMismatchException e){ //算法不匹配异常
            e.printStackTrace();
            map.put("msg","算法不匹配！");
        }catch (InvalidClaimException e){ //失效的payload异常
            e.printStackTrace();
            map.put("msg","失效的payload！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        map.put("state",false);
        //将map转化为json
        String result = JSONObject.toJSON(map).toString();
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(result);
        return false;
    }
}
