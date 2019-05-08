package com.sxkj.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * com.sxkj.util.YmlUtil
 *
 * @author zwd
 * @Description YmlUtil
 * @Date Create in 2018-07-18 0018 14:34
 * @Modified
 */
@Component
@ConfigurationProperties(prefix = "server.servlet.session")
public class YmlUtil {
    private long timeout;

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }
}
