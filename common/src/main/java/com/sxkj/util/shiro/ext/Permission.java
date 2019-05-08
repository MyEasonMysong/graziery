package com.sxkj.util.shiro.ext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.sxkj.util.shiro.ext.Permission
 *
 * @author zwd
 * @Description Permission
 * @Date Create in 2018-07-17 0017 16:46
 * @Modified
 * 使用示例：@Permission("编辑用户")
 * https://github.com/sharetop
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
    String value() default "";
}
