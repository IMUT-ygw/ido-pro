package com.ido.cloudprovider8081;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudProvider8081Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider8081Application.class, args);
    }

}
