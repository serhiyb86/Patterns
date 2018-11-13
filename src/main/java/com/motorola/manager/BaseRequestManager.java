/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.manager;

import com.google.gson.JsonObject;
import com.motorola.cloud.APIClient;
import com.motorola.constants.InterfaceConstants;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationErrorType;
import com.motorola.validation.ValidationResult;
import org.restlet.engine.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides manager for validation and translation of incoming HttpServletRequest request.
 */
public class BaseRequestManager {


	private final APIClient client = new APIClient();
	private BaseTranslator translator;
	private JsonObject payload;

	/**
	 * Method that validates incoming request for all required data
	 * @param request to validate
	 * @param expectedRequestType type of the request
	 * @return list of validation results
	 */
	public List<ValidationResult> validateRequest(HttpServletRequest request, String expectedRequestType) {
		String accessToken = request.getHeader(InterfaceConstants.HttpHeaderProperties.ACCESS_TOKEN);
		String apiURL = request.getHeader(InterfaceConstants.HttpHeaderProperties.API_URL);
		String spillmanVersion = request.getHeader(InterfaceConstants.HttpHeaderProperties.SPILLMAN_VERSION);
		payload = CadCloudUtils.extractPayloadFromHttpRequest(request);
		List<ValidationResult> validationResults = new ArrayList<>();

		if (payload == null) {
			validationResults.add(new ValidationResult("Payload is missing.", ValidationErrorType.MISSING_DATA));
		}
		else if (payload.get(InterfaceConstants.GeneralProperties.REQUEST_TYPE) == null) {
			validationResults.add(new ValidationResult("Request type is missing.", ValidationErrorType.MISSING_DATA));
		}
		else if (StringUtils.isNullOrEmpty(payload.get(InterfaceConstants.GeneralProperties.REQUEST_TYPE).getAsString())) {
			validationResults.add(new ValidationResult("Request type is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			String requestType = payload.get(InterfaceConstants.GeneralProperties.REQUEST_TYPE).getAsString();
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

		if (StringUtils.isNullOrEmpty(apiURL)) {
			validationResults.add(new ValidationResult("Cloud API URL is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			client.getConfig().setBasePath(apiURL);
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

	public JsonObject getPayload() {
		return payload;
	}

	public BaseTranslator getTranslator() {
		return translator;
	}
}
