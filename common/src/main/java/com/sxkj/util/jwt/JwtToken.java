package com.sxkj.util.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * com.sxkj.util.jwt.JwtToken
 *
 * @author zwd
 * @Description JwtToken
 * @Date Create in 2018-07-12 0012 14:27
 * @Modified
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
