package com.obatis;

import com.obatis.core.annotation.StartupLoadAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@StartupLoadAutoConfigure
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class ApplicationStartup {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartup.class, args);
    }

}
