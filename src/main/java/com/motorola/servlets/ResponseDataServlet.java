/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */

package com.motorola.servlets;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiExceptionModel;
import com.motorola.api.utils.ExceptionModel;
import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BookOnOffRequestManager;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.ResponseData;
import com.motorola.utils.CadCloudUtils;
import com.motorola.utils.LoggerUtils;
import com.motorola.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The implementation of endpoint servlet that called when error appears onPrem side
 */
@WebServlet(urlPatterns = "/data")
public class ResponseDataServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(ResponseDataServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BookOnOffRequestManager requestManager = new BookOnOffRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.NotificationProperties.RESPONSE_DATA_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			ResponseData responseData = requestManager.getTranslator().translateResponseData(requestManager.getPayload());
			String outgoingModel = CadCloudUtils.convertObjectToJsonString(responseData);
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				LoggerUtils.printJsonLogs(responseData);
				try {
					ModelApiResponse apiResponse = requestManager.responseData(responseData);
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(apiResponse));
				}
				catch (ApiException e) {
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
				}
				catch (Exception e) {
					LOGGER.error("Failed to send ResponseData.", e);
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(new ExceptionModel(e.getMessage())));
				}
			}
			else {
				respondFailure(response, requestManager.getTranslator().getValidationResults());
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}

}

