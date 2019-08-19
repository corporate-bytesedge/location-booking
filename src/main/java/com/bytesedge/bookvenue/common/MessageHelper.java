package com.bytesedge.bookvenue.common;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

public class MessageHelper {
	private MessageSource messageSource;
	private static Locale locale = Locale.getDefault();
	private static Logger logger = LoggerFactory.getLogger(MessageHelper.class);

	public String getMessage(String msgProperty) {
		try {
			return messageSource.getMessage(msgProperty, null, locale);
		} catch (NoSuchMessageException e) {
			logger.debug("Failed to parse message from message bundle, " + e.getMessage());
			throw e;
		}
	}

	public String getMessage(String msgProperty, List<Object> argList) {
		try {
			Object[] argArray = null;
			if (argList != null) {
				argArray = argList.toArray();
			}
			return messageSource.getMessage(msgProperty, argArray, locale);
		} catch (NoSuchMessageException e) {
			logger.debug("Failed to parse message from message bundle, " + e.getMessage());
			throw e;
		}
	}

	public String getMessage(String msgProperty, Object[] argArray) {
		try {
			return messageSource.getMessage(msgProperty, argArray, locale);
		} catch (NoSuchMessageException e) {
			logger.debug("Failed to parse message from message bundle, " + e.getMessage());
			throw e;
		}
	}

	public String getMessage(String msgProperty, String defaultMessage) {
		try {
			return messageSource.getMessage(msgProperty, null, locale);
		} catch (NoSuchMessageException e) {
			logger.debug("Failed to parse message from message bundle, " + e.getMessage());
			// throw e;
		}
		if (defaultMessage != null) {
			return defaultMessage;
		} else {
			throw new NoSuchMessageException("No Message found");
		}
	}

	/**
	 * @return the messageSource
	 */
	public MessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource
	 *            the messageSource to set
	 */
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
