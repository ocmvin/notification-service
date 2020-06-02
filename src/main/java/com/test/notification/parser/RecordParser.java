package com.test.notification.parser;

import com.test.notification.domain.*;

public interface RecordParser {
	
	Notification parse(String notificationRecord);

}
