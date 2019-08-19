package com.bytesedge.bookvenue.core;

public class ServerConfig {
	public static final String REDIS_CONN_PREFIX = "redis.connections.";
	public static String REDIS_USE_SNETINEL = "redis.use-sentinal";
	public static String REDIS_HOSTNAME = "redis.hostname.";
	public static String REDIS_IP_ADDR = "redis.ipaddr.";
	public static String REDIS_PORT = "redis.port."; 
	
	public static final String DIR_DATA_REPO = "/data/";

	public static boolean getBoolProperty(String key, boolean defaultVal) {
		return false;
	}

	public static int getIntegerProperty(String string, int defaultVal) {
		return defaultVal;
	}

	public static String getStringProperty(String string, String defaultVal) {
		return defaultVal;
	}
}