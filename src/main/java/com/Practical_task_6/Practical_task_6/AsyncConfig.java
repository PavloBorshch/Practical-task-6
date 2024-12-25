package com.Practical_task_6.Practical_task_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
    private final RetryableTask retryableTask;
    private final DelayedTask delayedTask;

    @Autowired
    public AsyncConfig(RetryableTask retryableTask, DelayedTask delayedTask){
        this.retryableTask = retryableTask;
        this.delayedTask = delayedTask;
    }
    @Bean(name = "taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Async-");
        executor.initialize();
        return executor;
    }

    public void executeTasks() {
        // Запускаємо задачу, яка буде повторюватись
        retryableTask.executeWithRetry(3, 5);

        // Запускаємо задачу, яка буде виконана через 15 секунд
        delayedTask.executeAfterDelay(15);
    }
}