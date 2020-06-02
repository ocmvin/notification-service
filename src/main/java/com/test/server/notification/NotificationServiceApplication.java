package com.test.server.notification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.server.notification.listener.RedisQueueListener;

@SpringBootApplication
public class NotificationServiceApplication {
	@Autowired
	private static RedisQueueListener listener;
	
	private static ExecutorService executorService=null;
	public NotificationServiceApplication(RedisQueueListener listener) {
	
			this.listener = listener;
	}
	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
		executorService=Executors.newSingleThreadExecutor();
		executorService.execute(listener);
	}
	
	
	




}
