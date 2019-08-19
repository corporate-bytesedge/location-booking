package com.bytesedge.bookvenue.common;

public interface SmsService {
	public void sendSms(String mobile, String message) throws Exception;
}