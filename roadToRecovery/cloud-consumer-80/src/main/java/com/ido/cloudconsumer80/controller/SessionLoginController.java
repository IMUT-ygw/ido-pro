package com.ido.cloudconsumer80.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ido.cloudconsumer80.pojo.User;
import com.ido.cloudconsumer80.service.UserService;
import com.ido.cloudconsumer80.utils.JWTUtils;
import com.netflix.client.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT:
 *      token string类型 ===>  x.y.x
 *      x:header（标题）  由两部分组成：令牌类型{typ:JWT} 和 签名算法{alg:HS256} 最后将这两个信息通过base64进行编码
 *      y:payload（负载） 用户信息可放在这里，比如用户名、权限等（别放敏感信息，会被解码），通过base64进行编码
 *      z:signature（签名） base64(x+y) +"签名"  对这串进行HS256签名算法进行签名
 *
 *      使用token验证时，服务端会先验签名，会先把得到的x、y部分，通过秘钥进行验签，服务端生成的部分与z进行比较，如果一致，信息没被篡改。
 *
 *
 */
@RestController
@Slf4j
@RequestMapping("user")
public class SessionLoginController {

    @Autowired
    private UserService userService;

    /**
     * 验证token
     * @param username
     * @param request
     * @param token
     * @return
     */
    @GetMapping("login/{username}")
    public Map<String,Object> login(@PathVariable("username") String username , HttpServletRequest request,String token){
        HashMap<String, Object> map = new HashMap<>();
        try {
            DecodedJWT tokenInfo = JWTUtils.getTokenInfo(token);
            request.getSession().setAttribute(username,"ok");
            request.getSession().setAttribute("username","ok");
            request.getSession().setAttribute("username","error");
            System.out.println(request.getSession().getAttribute(username));
            System.out.println(request.getSession().getAttribute("username"));
            map.put("state",true);
            map.put("msg","请求成功！");
            return map;
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
        return map;
    }

    /**
     * 首先进行用户登录获取token  123/123123
     * @param user
     * @return
     */
    @GetMapping("/jwt/login")
    public Map<String,Object> login(User user){
        log.info("用户名:[[]]",user.getUsername());
        log.info("秘密:[[]]",user.getPwd());
        HashMap<String, Object> map = new HashMap<>();
        try {
            User login = userService.login(user);
            HashMap<String, String> param = new HashMap<>();
            param.put("userId",login.getId().toString());
            param.put("userName",login.getUsername());
            String token = JWTUtils.getToken(param);
            map.put("state",true);
            map.put("msg","认证成功！");
            map.put("token",token);
        } catch (Exception e) {
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

}
