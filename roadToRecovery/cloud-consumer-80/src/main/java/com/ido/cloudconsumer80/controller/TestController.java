package com.ido.cloudconsumer80.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 *    get:
 *          想要获取响应数据用getForEntity
 *          ResponseEntity<String> body = restTemplate.getForEntity("http://CLOUD-PROVIDER/testParam?id={id}&name={name}", String.class, map);
 *          想要直接获取body使用getForObject
 *           String forObject = restTemplate.getForObject("http://CLOUD-PROVIDER/testParam?id={id}&name={name}", String.class, map);
 *    post：
 *         MultiValueMap post参数必须由这个map封装
 *    put:
 *         put请求后没有返回值
 *    delete:
 *          也没有返回值
 */
@RestController
public class TestController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("test")
    public String test(){
       //get方式   返回值类型为String
       //return restTemplate.getForEntity("http://localhost:8080/test",String.class).getBody();

       return restTemplate.getForEntity("http://CLOUD-PROVIDER/test",String.class).getBody();
    }



    @GetMapping("testParam")
    public String testParam(){
        //传参
        String[] param = {"100","ygw"};
        //第三个参数是请求地址的参数列表
        return restTemplate.getForEntity("http://CLOUD-PROVIDER/testParam?id={0}&name={1}",String.class,param).getBody();
    }

    @GetMapping("testParamMap")
    public String testParamMap(){
        //传参
        HashMap<String,Object> map = new HashMap<>();
        map.put("id",100);
        map.put("name","ygw");
        //第三个参数是请求地址的参数列表
       // ResponseEntity<String> body = restTemplate.getForEntity("http://CLOUD-PROVIDER/testParam?id={id}&name={name}", String.class, map);
        String forObject = restTemplate.getForObject("http://CLOUD-PROVIDER/testParam?id={id}&name={name}", String.class, map);
        return forObject;
    }



    @GetMapping("testParamPost")
    public String testParamPost(){
        //post类型传参必须由MultiValueMap封装
        MultiValueMap<String,Object> map = new LinkedMultiValueMap<>();
        map.add("body","{'name':'ygw'}");
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://CLOUD-PROVIDER/testParamPost", map, String.class);
        return "testParam8081:" + stringResponseEntity.getBody();
    }

}
