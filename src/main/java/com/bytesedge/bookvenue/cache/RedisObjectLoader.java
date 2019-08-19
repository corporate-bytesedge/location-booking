package com.bytesedge.bookvenue.cache;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class RedisObjectLoader<E> {
	private static Logger logger = Logger.getLogger(RedisObjectLoader.class);
	public abstract Class<?> getClazz();
	public abstract String getKey();
	public abstract E loadObject() throws Exception;
	public static ObjectMapper jsonApi = new ObjectMapper();
	static {
		jsonApi.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	public int getTTL() {
		return 3600;
	}

	public boolean isAutoSave() {
		return true;
	}

	public boolean isValid(E obj) {
		return true;
	}

	public E process() throws Exception {
		E obj = null;
		obj = this.getObject(obj);
		obj = loadObject();
		if (isAutoSave() && obj != null) {
			this.setObject(obj);
		}
		return obj;
	}

	private void setObject(E obj) {
		try {
			RedisCacheService.setWithExpiry(getKey(), jsonApi.writeValueAsString(obj), getTTL());
		} catch (Throwable t) {
			logger.error("Save cache:" + getKey() + ", Error="+ t.getMessage(), t);			
		}
	}

	private E getObject(E obj) {
		try { 
			String jsonObj = RedisCacheService.get(getKey());
			if (jsonObj != null) {
				obj = (E) new ObjectMapper().readValue(jsonObj, getClazz());
			}
		} catch (Throwable t) {
			logger.error("Get cache:" + getKey() +" Error=" + t.getMessage(), t);			
		}
		return obj;
	}
    	
}
