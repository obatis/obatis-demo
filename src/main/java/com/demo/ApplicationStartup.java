package com.demo;

import com.sbatis.core.annotation.StartupLoadAutoConfigure;
import org.springframework.boot.SpringApplication;

@StartupLoadAutoConfigure
public class ApplicationStartup {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStartup.class, args);
    }

}
