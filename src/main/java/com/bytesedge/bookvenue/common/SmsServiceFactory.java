package com.bytesedge.bookvenue.common;

public class SmsServiceFactory {
	private static SmsServiceFactory inst = null;
	private static String GATEWAY_TYPE_MSG91 = "MSG91";
	private static String GATEWAY_TYPE_HORIZON = "HORIZON";
	
	private SmsService service = null;
	
	public static SmsServiceFactory getInstance() {
		if(inst == null) {
			inst = new SmsServiceFactory();
		}
		return inst;
	}
	
	private SmsServiceFactory() {
		service = new SmsServiceMsg91();
	}

	public SmsService getService() {
		return service;
	}

	public void setService(SmsService service) {
		this.service = service;
	}
	
	
}