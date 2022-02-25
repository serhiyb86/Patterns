/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.google.json.JsonSanitizer;

/**
 * Base servlet class for all servlets
 */
abstract class BaseHttpServlet extends HttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(BaseHttpServlet.class);
	private static final String ERROR_MESSAGE = "Error occurred when trying to send the response. ";

	/**
	 * Respond with translated model and CAD IngestApi response.
	 * @param response to respond.
	 * @param responseString additional string with data to respond.
	 * @param ingestAPIResponse CAD Ingest API response.
	 */
	protected void respondWithTranslatedModel(HttpServletResponse response, String responseString, String ingestAPIResponse) {
		StringBuilder responseMessage = new StringBuilder("{ \"TranslatedData\": ");
		if (StringUtils.isNotBlank(responseString)) {
			responseMessage.append(responseString);
		}
		responseMessage.append(",");
		responseMessage.append("\"Response\": ");
		responseMessage.append(ingestAPIResponse);
		responseMessage.append("}");
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			String message = JsonSanitizer.sanitize(responseMessage.toString());
			outputStream.write(message.getBytes());
		}
		catch (IOException e) {
			LOGGER.error(ERROR_MESSAGE, e);
		}
	}

	/**
	 * Respond with translated model.
	 * @param response to respond.
	 * @param responseString additional string with data to respond.
	 */
	protected void respondWithTranslatedModel(HttpServletResponse response, String responseString) {
		StringBuilder responseMessage = new StringBuilder("{ \"TranslatedData\": ");
		if (StringUtils.isNotBlank(responseString)) {
			responseMessage.append(responseString);
		}
		responseMessage.append("}");
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			String message = JsonSanitizer.sanitize(responseMessage.toString());
			outputStream.write(message.getBytes());
		}
		catch (IOException e) {
			LOGGER.error(ERROR_MESSAGE, e);
		}
	}

	/**
	 * Respond with failure message
	 * @param response to respond
	 * @param validationResults list with errors
	 */
	protected void respondFailure(HttpServletResponse response, List<ValidationResult> validationResults) {
		StringBuilder responseMessage = new StringBuilder("{\"error\" : \"Error happened during processing the request. See details below.\",\"errorMessage\" ");
		String responseString = CadCloudUtils.convertObjectToJsonString(validationResults);
		try (ServletOutputStream outputStream = response.getOutputStream()) {
			if (!StringUtils.isBlank(responseString)) {
				responseMessage.append(responseString);
			}
			responseMessage.append("}");
			String message = JsonSanitizer.sanitize(responseMessage.toString());
			outputStream.write(message.getBytes());
		}
		catch (IOException e) {
			LOGGER.error(ERROR_MESSAGE, e);
		}
	}

}
