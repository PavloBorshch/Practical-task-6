package com.Practical_task_6.Practical_task_6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RetryableTask {

    private static final Logger log = LoggerFactory.getLogger(RetryableTask.class);

    @Async
    public void executeWithRetry(int maxAttempts, int delaySeconds) {
        int attempts = 0;
        boolean success = false;

        while (attempts < maxAttempts && !success) {
            attempts++;
            try {
                simulateTask();
                success = true;
                log.info("Task completed successfully after {} attempt(s).", attempts);
            } catch (Exception e) {
                log.error("Task failed on attempt {}: {}", attempts, e.getMessage());
                if (attempts < maxAttempts){
                    try{
                        Thread.sleep(delaySeconds * 1000);
                    } catch (InterruptedException ex){
                        Thread.currentThread().interrupt();
                        log.error("Thread interrupted during delay ", ex);
                        return;

                    }
                }
            }
        }

        if (!success) {
            log.error("Task failed after {} attempts.", maxAttempts);
        }
    }

    private void simulateTask() {
        // Тут імітуємо виконання задачі, яка може завершитися з помилкою
        if (Math.random() < 0.5) {  // 50% ймовірність помилки
            throw new RuntimeException("Simulated task failure.");
        }
        log.info("Task was simulated");
    }
}