package com.test.notification.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.test.notification.domain.Notification;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class NotificationService {
	
	
	

	//@Autowired
	//private NotificationRepo notificationRepo;

	//TODO: Save notification history
	
	public void create(Notification notification) {
		// TODO Auto-generated method stub
		
	}
}
