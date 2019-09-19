package com.blog.model;

import com.blog.utils.UUIdGenId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Table(name = "tb_user")
public class User {
    @Id
    @KeySql(genId = UUIdGenId.class)
    private String id;
    private String nickname;//昵称
    private String username;//用户名
    @JsonIgnore
    private String password;//密码
    private String phone;//电话号码
    @JsonIgnore
    private String salt;// 密码的盐值
    @JsonIgnore
    private String status;//状态


    private Timestamp createtime;//创建时间
    private Timestamp updatetime;//最后一次修改时间

}
