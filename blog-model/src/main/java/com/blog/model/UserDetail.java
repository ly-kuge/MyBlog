package com.blog.model;

import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Table(name = "tb_user_detail")
public class UserDetail {
    @Id
    private String userId;//用户id
    private Timestamp birthday;//生日
    private String introduction;//介绍
    private String sex;//性别
    private String website;//个人网址
    private String email;//邮箱
    private String headimage;//头像
    private Timestamp createtime;//创建时间
    private Timestamp updatetime;//最后一次修改时间
}
