package com.ido.cloudconsumer80;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

@MapperScan("com.ido.cloudconsumer80.mapper")
@EnableEurekaClient
@SpringBootApplication
public class CloudConsumer80Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer80Application.class, args);
    }

}
