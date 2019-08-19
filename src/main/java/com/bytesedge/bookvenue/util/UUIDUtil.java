package com.bytesedge.bookvenue.util;

import java.security.MessageDigest;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UUIDUtil {
	
	private static final Logger log = LoggerFactory.getLogger(UUIDUtil.class);
	
	private UUIDUtil() {}
	
	public static String generateUUID(String seedString) {
		try {
			byte[] bytesOfMessage = seedString.getBytes("UTF-8");
	    	MessageDigest md = MessageDigest.getInstance("MD5");
	    	byte[] thedigest = md.digest(bytesOfMessage);
	        UUID uuid = UUID.nameUUIDFromBytes(thedigest);
	        return uuid.toString();
		} catch(Exception e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}
	
	public static String getUuid(int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append((char) (Math.random() * 10 + '0'));
		}
		return sb.toString();
	}
}