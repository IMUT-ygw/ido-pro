package com.ido.cloudprovider8080.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TestController {


    @GetMapping("test")
    public String test(){
        UUID uuid = UUID.randomUUID();
        return "8080" + uuid.toString();
    }

    @GetMapping("testParam")
    public String testParam(@RequestParam("id") Integer id,@RequestParam("name") String name){
        return "testParam8080:" + id + name;
    }


    @PostMapping("testParamPost")
    public String testParam(@RequestBody String body){
        return "testParam8080:" + body;
    }
}
