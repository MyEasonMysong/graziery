package com.sxkj.util.shiro.ext;

import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

/**
 * com.sxkj.util.shiro.ext.ShiroAdvisor
 *
 * @author zwd
 * @Description ShiroAdvisor
 * @Date Create in 2018-07-17 0017 17:56
 * @Modified
 */
public class ShiroAdvisor extends AuthorizationAttributeSourceAdvisor {
    public ShiroAdvisor(){
        setAdvice(new PermissionAopInterceptor());
    }
    @Override
    public boolean matches(Method method, Class targetClass) {
        try {
            Method m = method;
            if (targetClass != null) {
                m = targetClass.getMethod(m.getName(), m.getParameterTypes());
                return this.isFrameAnnotation(m);
            }
            return super.matches(method, targetClass);
        } catch (Exception e) {
            return false;
        }
    }
    private boolean isFrameAnnotation(Method method) {
        return null != AnnotationUtils.findAnnotation(method, Permission.class);
    }
}
