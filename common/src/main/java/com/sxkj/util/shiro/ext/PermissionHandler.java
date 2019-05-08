package com.sxkj.util.shiro.ext;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;

/**
 * com.sxkj.util.shiro.ext.PermissionHandler
 *
 * @author zwd
 * @Description PermissionHandler
 * @Date Create in 2018-07-17 0017 17:41
 * @Modified
 */
public class PermissionHandler extends AuthorizingAnnotationHandler {
    public PermissionHandler(){
        super(Permission.class);
    }
    @Override
    public void assertAuthorized(Annotation annotation) throws AuthorizationException {
        if(annotation instanceof Permission){
            Permission permissions = (Permission) annotation;
            String perms = permissions.value();
            Subject subject = getSubject();
            subject.checkPermission(perms);
        }
    }
}
