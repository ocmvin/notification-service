package com.test.server.notification.listener;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.notification.parser.RecordParser;
import com.test.notification.redis.repo.RedisUtil;
import com.test.server.notification.config.AppPropertiesConfig;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class RedisQueueListener implements Runnable {

	@Autowired
	private RedisUtil<String> redisUtil;

	@Autowired
	AppPropertiesConfig config;

	private Supplier<RecordParser> fileProcessorBeanFactory;
	
	public RedisQueueListener(Supplier<RecordParser> fileProcessorBeanFactory) {
		this.fileProcessorBeanFactory = fileProcessorBeanFactory;
		
	}

	public void run() {

		log.info("Running Listener");

		while (true) {
			String message = redisUtil.readQueue(config.getQueue(), config.getTimeOut());
			log.info("Listener  MESSAGE RECEIVED " + message);
			processMessage(message);
			
		}
		
	}

	private void processMessage(String message) {
		fileProcessorBeanFactory.get().parse(message.toString());
	}

}
