package com.blog.client;

import com.blog.UserService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author bystander
 * @date 2018/10/1
 */
@FeignClient(value = "blog-user-service")
public interface UserClient extends UserService {
}
