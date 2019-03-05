/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.utils;

import com.motorola.models.representation.ApiError;
import com.motorola.models.representation.ResponseNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation for logger appender.
 *  The class specify in log4j.properties like appender
 */
public class LoggerUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtils.class);
	private static final String MESSAGE_DELIMITER = ".";

	/**
	 * Prints logs in the json format for Splunk incoming file.
	 * Logs with ERROR level weill provides the error code, the root cause why error appears
	 * Logs with INFO level will provides the information
	 * @param notification
	 */
	public static void printJsonLogs(ResponseNotification notification) {
		String nature = notification.getResultNature();
		// log information message
		if (notification.getError() != null) {
			ApiError error = notification.getError();
			// construct error message
			String message = nature + MESSAGE_DELIMITER
				+ error.getErrorCode() + MESSAGE_DELIMITER + error.getMessage();
			LOGGER.error(message);
		}
		else {
			LOGGER.info(nature);
		}
	}

}
