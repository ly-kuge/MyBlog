package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class BlogApiGateway {
    public static void main(String[] args) {
        SpringApplication.run(BlogApiGateway.class,args);
    }
}
