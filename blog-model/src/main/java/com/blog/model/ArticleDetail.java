package com.blog.model;

import lombok.Data;
import java.sql.Timestamp;

@Data
public class ArticleDetail {
    private int articleId;
    private String content;//内容
    private Timestamp createtime;//创建时间
    private Timestamp updatetime;//最后一次修改时间
}
