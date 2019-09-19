package com.blog.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.properties
 * @Author: ly
 * @CreateTime: 2019-08-30 20:48
 * @Description:
 */
@ConfigurationProperties(prefix = "blog.filter")
public class FilterProperties {

    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}