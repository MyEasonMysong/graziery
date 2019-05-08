package com.sxkj.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * com.sxkj.util.Constants
 *
 * @author zwd
 * @Description Constants
 * @Date Create in 2018-07-23 0023 10:55
 * @Modified
 */
public final class Constants {
    public static final String SESSION_USER_ID = "userId";


    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    public static Long getUserId(){
        return (Long) getRequest().getAttribute(Constants.SESSION_USER_ID);
    }
}
