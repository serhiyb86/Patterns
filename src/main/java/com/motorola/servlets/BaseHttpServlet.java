/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.google.gson.JsonObject;
import com.motorola.cloud.APIClient;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import com.motorola.validation.ValidationErrorType;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.motorola.constants.InterfaceConstants.ACCESS_TOKEN;
import static com.motorola.constants.InterfaceConstants.REQUEST_TYPE;
import static com.motorola.constants.InterfaceConstants.SPILLMAN_VERSION;

/**
 * Base servlet class for all servlets
 */
abstract class BaseHttpServlet extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseHttpServlet.class);

	protected final APIClient client = new APIClient();
	protected BaseTranslator translator = null;
	protected String accessToken = null;
	protected String spillmanVersion = null;
	protected String requestType = null;
	protected JsonObject payload = null;

	/**
	 * Method that validates incoming request for all required data
	 * @param request to validate
	 * @param expectedRequestType type of the request
	 * @return list of validation results
	 */
	protected List<ValidationResult> validateRequest(HttpServletRequest request, String expectedRequestType) {
		accessToken = request.getHeader(ACCESS_TOKEN);
		spillmanVersion = request.getHeader(SPILLMAN_VERSION);
		payload = CadCloudUtils.extractPayloadFromHttpRequest(request);
		List<ValidationResult> validationResults = new ArrayList<>();

		if (payload == null) {
			validationResults.add(new ValidationResult("Payload is missing.", ValidationErrorType.MISSING_DATA));
		}
		else if (payload.get(REQUEST_TYPE) == null) {
			validationResults.add(new ValidationResult("Request type is missing.", ValidationErrorType.MISSING_DATA));
		}
		else if (StringUtils.isNullOrEmpty(payload.get(REQUEST_TYPE).getAsString())) {
			validationResults.add(new ValidationResult("Request type is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			requestType = payload.get(REQUEST_TYPE).getAsString();
			if (!expectedRequestType.equals(requestType)) {
				validationResults.add(new ValidationResult(
					String.format("Request type is incorrect. Expected: %s, but was: %s.", expectedRequestType, requestType),
					ValidationErrorType.UNEXPECTED_DATA));
			}
		}

		if (StringUtils.isNullOrEmpty(accessToken)) {
			validationResults.add(new ValidationResult("Access token is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			client.getConfig().getSecurityConfig().configureAuthApi_key(accessToken);
		}

		if (StringUtils.isNullOrEmpty(spillmanVersion)) {
			validationResults.add(new ValidationResult("Spillman version is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			translator = TranslatorsFactory.getTranslator(spillmanVersion);
			if (translator == null) {
				validationResults.add(
					new ValidationResult(
						String.format("Error getting translator for Spillman version: %s.", spillmanVersion),
						ValidationErrorType.UNEXPECTED_DATA));
			}
		}

		return validationResults;
	}

	/**
	 * Respond with success message
	 * @param response to respond
	 * @param responseString additional string with data to respond
	 */
	protected void respondSuccess(HttpServletResponse response, String responseString) {
		StringBuilder responseMessage = new StringBuilder("Request was successfully processed.");
		if (!StringUtils.isNullOrEmpty(responseString)) {
			responseMessage.append(responseString);
		}
		try {
			response.getOutputStream().write(responseMessage.toString().getBytes());
		}
		catch (IOException e) {
			LOGGER.error("Error occured when trying to send the response.");
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
		try {
			if (!StringUtils.isNullOrEmpty(responseString)) {
				responseMessage.append(responseString);
			}
			response.getOutputStream().write(responseMessage.toString().getBytes());
		}
		catch (IOException e) {
			LOGGER.error("Error occured when trying to send the response.");
		}
	}
}
