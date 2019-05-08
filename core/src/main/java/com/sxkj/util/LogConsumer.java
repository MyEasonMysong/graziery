package com.sxkj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

/**
 * com.sxkj.util.LogConsumer
 *
 * @author zwd
 * @Description LogConsumer
 * @Date Create in 2018-06-20 0020 11:17
 * @Modified
 */
@Component
public class LogConsumer implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(LogConsumer.class);
    public static final int DEFAULT_BATCH_SIZE = 64;
    @Autowired
    private LogQueue auditLogQueue;
    private int batchSize = DEFAULT_BATCH_SIZE;
    private boolean active = true;
    private Thread thread;

    @PostConstruct
    public void init() {
        thread = new Thread(this);
        thread.start();
    }

    @PreDestroy
    public void close() {
        active = false;
    }
    @Override
    public void run() {
        while (active) {
            execute();
        }
    }

    public void execute() {
        List<String> auditDtos = new ArrayList<String>();
        try {
            int size = 0;
            while (size < batchSize) {
                String auditLog = auditLogQueue.poll();

                if (auditLog == null) {
                    break;
                }
                System.out.println(auditLog+"==============================================");
                auditDtos.add(auditLog);
                size++;
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage(), ex);
        }
        if(!auditDtos.isEmpty()){
            // todo 批量添加日志
        }
    }
}
