package com.blog.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Article {
    private  int id;
    private String title;//标题
    private int userId;//用户id
    private String summary;//概要
    private  int classId;//分类id
    private int pollCount;//点赞数
    private  int commentCount;//评论数
    private  int readCount;//阅读数
    private  int tagId;//标签id
    private Timestamp createtime;//创建时间
    private  Timestamp updatetime;//最后一次修改时间



}
