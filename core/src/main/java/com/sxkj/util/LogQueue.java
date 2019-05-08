package com.sxkj.util;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * com.sxkj.util.LogQueue
 *
 * @author zwd
 * @Description LogQueue
 * @Date Create in 2018-06-20 0020 11:15
 * @Modified
 */
@Component
public class LogQueue {
    private BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();

    public void add(HttpServletRequest request) {
        Map<String, String> map = RequestInfo.getRequestInfo(request);
        String msg = new Gson().toJson(map);
        blockingQueue.add(msg);
    }

    public String poll() throws InterruptedException {
        return blockingQueue.poll(1, TimeUnit.SECONDS);
    }
}
