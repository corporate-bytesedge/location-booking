package com.bytesedge.bookvenue.common;

import com.bytesedge.bookvenue.mq.RedisMqService;

public class MqService {
	public static String sendMessage(String queueName, String msg) {
		return RedisMqService.sendMessage(queueName, msg);
	}
}