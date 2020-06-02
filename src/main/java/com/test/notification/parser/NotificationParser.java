package com.test.notification.parser;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.notification.domain.Notification;
import com.test.notification.service.NotificationService;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NotificationParser implements RecordParser {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private NotificationService notificationService;
	
	@Override
	public Notification parse(String record) {
		
		Notification notification =null;
		try {
			notification = objectMapper.readValue(record, Notification.class);
		} catch (JsonMappingException e) {
			log.error("Exception while Mapping ",e);
		} catch (JsonProcessingException e) {
			log.error("Exception while Processing ",e);
		}
		
		log.info("Parsed Transaction : "+record);
		notificationService.create(notification);
		return notification;
	}
}
