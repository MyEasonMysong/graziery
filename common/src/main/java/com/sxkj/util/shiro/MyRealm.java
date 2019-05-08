package com.sxkj.util.shiro;

import com.sxkj.gaia.model.User;
import com.sxkj.gaia.service.UserService;
import com.sxkj.util.jwt.JwtToken;
import com.sxkj.util.jwt.JwtUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * com.sxkj.shiro.MyRealm
 *
 * @author zwd
 * @Description MyRealm
 * @Date Create in 2018-07-12 0012 14:23
 * @Modified
 */
@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;


    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        try {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            String token = (String) principals.getPrimaryPrincipal();
            User user = userService.findByLoginName(JwtUtil.getUserName(token));
            Set<String> permission = user.permissions(user.getResources());
            // todo 添加权限
            // todo 通过自定义注解Permission的value控制访问
            if(permission==null||permission.isEmpty()){
                permission = new HashSet<>(16);
            }
            // todo 测试用权限
            permission.add("self.test");
            simpleAuthorizationInfo.addStringPermissions(permission);
            return simpleAuthorizationInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }

    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) {
        // todo 调用业务方法根据userName获取用户信息
        try {
            String token = (String) auth.getCredentials();
            if(JwtUtil.isTokenExpired(token)){
                JwtUtil.refreshToken(token);
            }else {
                throw new AuthenticationException("请重新登录!");
            }
            String userName = JwtUtil.getUserName(token);
            User user = userService.findByLoginName(userName);
            if (user == null) {
                throw new AuthenticationException("User didn't existed!");
            }
            return new SimpleAuthenticationInfo(token, token, getName());
        } catch (Exception e) {
            throw new RuntimeException("get user info failed");
        }
    }
}
