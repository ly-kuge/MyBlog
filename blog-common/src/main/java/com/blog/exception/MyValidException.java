package com.blog.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.exception
 * @Author: ly
 * @CreateTime: 2019-08-31 14:52
 * @Description:
 */
@Getter
public class MyValidException extends RuntimeException {
    private BindingResult bindingResult;

    public MyValidException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
