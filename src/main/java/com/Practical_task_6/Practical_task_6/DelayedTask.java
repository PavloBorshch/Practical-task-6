package com.Practical_task_6.Practical_task_6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class DelayedTask {
    private static final Logger log = LoggerFactory.getLogger(DelayedTask.class);

    @Async
    public void executeAfterDelay(int delaySeconds){
        try{
            Thread.sleep(delaySeconds * 1000);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
            log.error("Thread was interrupted while delaying ",e);
            return;

        }
        log.info("{} seconds after start", delaySeconds);
    }
}