package com.sxkj.util.shiro.ext;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

import java.lang.annotation.Annotation;

/**
 * com.sxkj.util.shiro.ext.PermissionMethodInterceptor
 *
 * @author zwd
 * @Description PermissionMethodInterceptor
 * @Date Create in 2018-07-17 0017 17:47
 * @Modified
 */
public class PermissionMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {
    public PermissionMethodInterceptor(){
        super(new PermissionHandler());
    }

    public PermissionMethodInterceptor(AnnotationResolver resolver) {
        super(new PermissionHandler(),resolver);
    }
    @Override
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException{
        try {
            Annotation annotation = getAnnotation(mi);
            ((PermissionHandler) getHandler()).assertAuthorized(getAnnotation(mi));
        } catch (AuthorizationException e) {
            if (e.getCause() == null) {
                e.initCause(new AuthorizationException("Not authorized to invoke method: " + mi.getMethod()));
            }
            throw e;
        }
    }
}
