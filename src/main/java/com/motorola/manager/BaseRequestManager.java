/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.manager;

import com.google.gson.JsonObject;
import com.motorola.api.utils.ApiClient;
import com.motorola.constants.InterfaceConstants;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.TranslatorsFactory;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationErrorType;
import com.motorola.validation.ValidationResult;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides manager for validation and translation of incoming HttpServletRequest request.
 */
public abstract class BaseRequestManager {

	private final TranslatorsFactory translatorsFactory = new TranslatorsFactory();
	private BaseTranslator translator;
	private JsonObject payload;

	protected final ApiClient apiClient = new ApiClient();
	protected String accessToken;

	/**
	 * Method that validates incoming request for all required data
	 * @param request to validate
	 * @param expectedRequestType type of the request
	 * @return list of validation results
	 */
	public List<ValidationResult> validateRequest(HttpServletRequest request, String expectedRequestType) {
		accessToken = request.getHeader(InterfaceConstants.HttpHeaderProperties.ACCESS_TOKEN);
		String apiURL = request.getHeader(InterfaceConstants.HttpHeaderProperties.API_URL);
		String spillmanVersion = request.getHeader(InterfaceConstants.HttpHeaderProperties.SPILLMAN_VERSION);
		String customerId = request.getHeader(InterfaceConstants.HttpHeaderProperties.CUSTOMER_ID);
		String correlationId = request.getHeader(InterfaceConstants.HttpHeaderProperties.CORRELATION_ID);
		payload = CadCloudUtils.extractPayloadFromHttpRequest(request);
		List<ValidationResult> validationResults = new ArrayList<>();

		if (payload == null) {
			validationResults.add(new ValidationResult("Payload is missing.", ValidationErrorType.MISSING_DATA));
		}
		else if (payload.get(InterfaceConstants.GeneralProperties.REQUEST_TYPE) == null) {
			validationResults.add(new ValidationResult("Request type is missing.", ValidationErrorType.MISSING_DATA));
		}
		else if (StringUtils.isBlank(payload.get(InterfaceConstants.GeneralProperties.REQUEST_TYPE).getAsString())) {
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

		if (StringUtils.isBlank(accessToken)) {
			validationResults.add(new ValidationResult("Access token is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			apiClient.setAccessToken(accessToken);
		}

		if (StringUtils.isBlank(apiURL)) {
			validationResults.add(new ValidationResult("Cloud API URL is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			apiClient.setBasePath(apiURL);
		}

		if (StringUtils.isBlank(spillmanVersion)) {
			validationResults.add(new ValidationResult("Spillman version is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			translator = translatorsFactory.getTranslator(spillmanVersion);
			if (translator == null) {
				validationResults.add(
					new ValidationResult(
						String.format("Error getting translator for Spillman version: %s.", spillmanVersion),
						ValidationErrorType.UNEXPECTED_DATA));
			}
		}

		if (StringUtils.isBlank(customerId)) {
			validationResults.add(new ValidationResult("customerId is missing.", ValidationErrorType.MISSING_DATA));
		}
		else {
			apiClient.setCustomerId(customerId);
		}

		if (StringUtils.isNotBlank(correlationId)) {
			apiClient.setCorrelationId(correlationId);
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
