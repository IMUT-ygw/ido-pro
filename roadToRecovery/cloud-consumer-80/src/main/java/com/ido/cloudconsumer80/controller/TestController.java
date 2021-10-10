package com.ido.cloudconsumer80.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("test")
    public String test(){
       //get方式   返回值类型为String
       return restTemplate.getForEntity("http://localhost:8080/test",String.class).getBody();
    }
}
