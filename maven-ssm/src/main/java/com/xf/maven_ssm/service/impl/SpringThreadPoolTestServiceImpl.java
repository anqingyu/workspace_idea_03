package com.xf.maven_ssm.service.impl;

import com.xf.maven_ssm.service.SpringThreadPollTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class SpringThreadPoolTestServiceImpl implements SpringThreadPollTestService{
    @Autowired
    private ThreadPoolTaskExecutor executor;

    public boolean testTaskThread() {
        for (int i = 0; i < 10; i++) {
            TaskThreadSender taskThreadSender = new TaskThreadSender(executor);
            taskThreadSender.send("name"+i);
        }
        return false;
    }
}

class TaskThreadSender {
    private static Logger log = LoggerFactory.getLogger(TaskThreadSender.class);

    private ThreadPoolTaskExecutor executor;

    public TaskThreadSender(ThreadPoolTaskExecutor executor) {
        this.executor = executor;
    }

    public void send(String fileName){
        executor.execute(new Runnable() {
            public void run() {
                long beginTime = System.currentTimeMillis();
                log.info("{} 开始复制...",Thread.currentThread().getName());

                //做耗时操作

                long endTime = System.currentTimeMillis();
                log.info("{} 复制完成，耗时{}ms",Thread.currentThread().getName(),endTime-beginTime);
            }
        });
    }
}