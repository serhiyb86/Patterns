/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Base servlet class for all servlets
 */
abstract class BaseHttpServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseHttpServlet.class);

	/**
	 * Respond with translated model.
	 * @param response to respond.
	 * @param responseString additional string with data to respond.
	 */
	protected void respondWithTranslatedModel(HttpServletResponse response, String responseString) {
		StringBuilder responseMessage = new StringBuilder("Translated Data: ");
		if (!StringUtils.isNullOrEmpty(responseString)) {
			responseMessage.append(responseString);
		}
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			outputStream.write(responseMessage.toString().getBytes());
		}
		catch (IOException e) {
			LOGGER.error("Error occurred when trying to send the response.");
		}
	}

	/**
	 * Respond with failure message
	 * @param response to respond
	 * @param validationResults list with errors
	 */
	protected void respondFailure(HttpServletResponse response, List<ValidationResult> validationResults) {
		StringBuilder responseMessage = new StringBuilder("Error happened during processing the request. See details below.");
		String responseString = CadCloudUtils.convertObjectToJsonString(validationResults);
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			if (!StringUtils.isNullOrEmpty(responseString)) {
				responseMessage.append(responseString);
			}
			outputStream.write(responseMessage.toString().getBytes());
		}
		catch (IOException e) {
			LOGGER.error("Error occured when trying to send the response.");
		}
	}

}
