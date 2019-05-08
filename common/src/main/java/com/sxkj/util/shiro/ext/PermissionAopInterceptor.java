package com.sxkj.util.shiro.ext;

import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;

/**
 * com.sxkj.util.shiro.ext.PermissionAopInterceptor
 *
 * @author zwd
 * @Description PermissionAopInterceptor
 * @Date Create in 2018-07-17 0017 17:54
 * @Modified
 */
public class PermissionAopInterceptor extends AopAllianceAnnotationsAuthorizingMethodInterceptor {
    public PermissionAopInterceptor(){
        super();
        // 添加自定义的注解拦截器
        this.methodInterceptors.add(new PermissionMethodInterceptor(new SpringAnnotationResolver()));
    }
}
