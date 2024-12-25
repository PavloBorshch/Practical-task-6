package com.Practical_task_6.Practical_task_6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
@EnableAsync
public class PracticalTask6Application {

	private final AsyncConfig asyncConfig;
	@Autowired
	public PracticalTask6Application(AsyncConfig asyncConfig) {
		this.asyncConfig = asyncConfig;
	}

	public static void main(String[] args) {
		SpringApplication.run(PracticalTask6Application.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void executeTasksAfterStartup(){
		asyncConfig.executeTasks();
	}
}