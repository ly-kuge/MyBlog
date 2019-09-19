package com.blog.utils;

import tk.mybatis.mapper.genid.GenId;

import java.util.UUID;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.utils
 * @Author: ly
 * @CreateTime: 2019-08-31 15:27
 * @Description:
 */
public class UUIdGenId implements GenId<String> {
    @Override
    public String genId(String s, String s1) {
        return UUID.randomUUID().toString().replace("-","");
    }
}
