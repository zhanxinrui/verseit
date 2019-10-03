package com.screwmachine55open.verseit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
//@EnableCaching
public class VerseitApplication {

    public static void main(String[] args) {
        SpringApplication.run(VerseitApplication.class, args);
    }

}
