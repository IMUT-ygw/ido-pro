package com.ido.cloudeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

//开启注册中心服务端
@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaServer9002Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudEurekaServer9002Application.class, args);
    }

}
