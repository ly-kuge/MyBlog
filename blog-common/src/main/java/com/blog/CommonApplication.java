package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @BelongsProject: blog
 * @BelongsPackage: PACKAGE_NAME
 * @Author: ly
 * @CreateTime: 2019-08-27 15:02
 * @Description:
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class,args);
    }
}
