package com.blog.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.vo
 * @Author: ly
 * @CreateTime: 2019-08-28 08:35
 * @Description:
 */
@Getter
@Setter
public class ResultBean {

    private static final int success = 0;
    private static final int fail = 1;
    private int code = success;
    private String msg;
    private Object data;
    private int count = 0;

    protected ResultBean() {
        super();
    }

    protected ResultBean(int code) {
        this.code = code;
    }

    public static ResultBean success(String msg, Object data, int count) {
        ResultBean resultBean = new ResultBean(success);
        resultBean.setMsg(msg);
        resultBean.setData(data);
        resultBean.setCount(count);
        return resultBean;
    }

    public static ResultBean success(String msg, Object data) {
        ResultBean resultBean = new ResultBean(success);
        resultBean.setMsg(msg);
        resultBean.setData(data);
        return resultBean;
    }

    public static ResultBean success(String msg) {
        ResultBean resultBean = new ResultBean(success);
        resultBean.setMsg(msg);
        return resultBean;
    }

    public static ResultBean fail(String msg) {
        ResultBean resultBean = new ResultBean(fail);
        resultBean.setMsg(msg);
        return resultBean;
    }

    public static ResultBean fail(String msg, Object data) {
        ResultBean resultBean = new ResultBean(fail);
        resultBean.setMsg(msg);
        resultBean.setData(data);
        return resultBean;
    }
}
