package com.blog.advice;

import com.blog.enums.ExceptionEnum;
import com.blog.exception.LyException;
import com.blog.exception.MyValidException;
import com.blog.vo.ExceptionResult;
import com.blog.vo.ResultBean;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ly
 * @date 2019/9/15
 * <p>
 * 自定义异常处理
 */
@Slf4j
@ControllerAdvice
public class BasicExceptionHandler {


    @ExceptionHandler(LyException.class)
    public ResponseEntity<ResultBean> handleException(LyException e) {
        return ResponseEntity.ok(ResultBean.fail(e.getExceptionEnum().message()));
    }

    /**
     * 数据校验异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MyValidException.class)
    public ResponseEntity<ResultBean> exception(MyValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();

        StringBuilder sb = new StringBuilder();
        allErrors.forEach(objectError -> {
            FieldError fieldError = (FieldError) objectError;
            sb.append(fieldError.getDefaultMessage()).append(",");
        });
        return ResponseEntity.ok(ResultBean.fail(sb.toString()));
    }
}
