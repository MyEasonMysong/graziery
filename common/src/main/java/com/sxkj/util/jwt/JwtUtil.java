package com.sxkj.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sxkj.util.YmlUtil;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * com.sxkj.util.jwt.JwtTokenUtil
 *
 * @author zwd
 * @Description JwtTokenUtil
 * @Date Create in 2018-07-12 0012 15:13
 * @Modified
 */
@Component
public class JwtUtil implements Serializable {

    /**
     * 密钥
     */
    private static final String SECRET = "ThisIsSecret";
    /**
     * 令牌有效期 1800秒
     */
    private static final Long EXPIRATION = 1800L;

    private static final String TOKEN_KEY = "userName";

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(TOKEN_KEY).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            Date expiration = jwt.getExpiresAt();
            return !expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            DecodedJWT jwt = JWT.decode(token);
            refreshedToken = generateToken(jwt.getClaim(TOKEN_KEY).asString());
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token    令牌
     * @param userName 用户名
     * @return 是否有效
     */
    public static Boolean validateToken(String token, String userName) throws UnsupportedEncodingException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = verifier.verify(token);
        String username = jwt.getClaim(TOKEN_KEY).asString();
        if (null == userName || "".equals(username)) {
            return false;
        }
        return (username.equals(userName) && isTokenExpired(token));
    }

    /**
     * 生成签名
     *
     * @param username 用户名
     * @return 加密的token
     */
    public static String generateToken(String username) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            // 附带username信息
            return JWT.create()
                    .withClaim(TOKEN_KEY, username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String userName = "123";
        String token = generateToken(userName);
        System.out.println(token);
        System.out.println(getUserName(token));
        System.out.println(isTokenExpired(token));

        Thread.sleep(2000);
        token = refreshToken(token);
        System.out.println(token);
//        System.out.println(claims.get(TOKEN_KEY)+"   "+claims.get(CREATED));

    }
}
