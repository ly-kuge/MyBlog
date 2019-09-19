package com.blog.Constatnt;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: blog
 * @BelongsPackage: com.blog.Constatnt
 * @Author: ly
 * @CreateTime: 2019-08-30 15:38
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
public enum UserConstant {
    Normal_Status("0"),//正常状态
    Freeze_Status("1"),//冻结状态
    KEY_PREFIX("user:verify:code:")
    ;

    String value;
    public String value() {
        return this.value;
    }
}
