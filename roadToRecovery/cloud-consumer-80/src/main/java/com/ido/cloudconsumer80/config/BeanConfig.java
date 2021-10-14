package com.ido.cloudconsumer80.config;

import com.ido.cloudconsumer80.interceptor.JWTInterceptor;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.management.MXBean;

@Configuration
public class BeanConfig implements WebMvcConfigurer {

    /**
     * 远程调用
     * @LoadBalanced rabbin提供的负载均衡策略
     * @return
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    /**
     * 写此方法会覆盖掉@LoadBalance默认的轮询策略
     * @return
     */
    @Bean
    public IRule iRule(){
        //随机负载均衡策略
        //return new RandomRule();
        //重试策略
        return new RetryRule();
    }


    /**
     * 配置登录拦截器  首先要访问
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor()).addPathPatterns("/*").excludePathPatterns("/user/**");
    }
}
