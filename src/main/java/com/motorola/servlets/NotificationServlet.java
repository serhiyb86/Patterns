/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */

package com.motorola.servlets;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiExceptionModel;
import com.motorola.api.utils.ExceptionModel;
import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BookOnOffRequestManager;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.utils.CadCloudUtils;
import com.motorola.utils.LoggerUtils;
import com.motorola.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The implementation of endpoint servlet that called when error appears onPrem side
 */
@WebServlet(urlPatterns = "/notification")
public class NotificationServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookOnOffRequestManager requestManager = new BookOnOffRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.NotificationProperties.ERROR_NOTIFICATION_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			ResponseNotification responseNotification = requestManager.getTranslator().translateResponseNotification(requestManager.getPayload());
			String outgoingModel = CadCloudUtils.convertObjectToJsonString(responseNotification);
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				LoggerUtils.printJsonLogs(responseNotification);
				try {
					ModelApiResponse apiResponse = requestManager.responseNotification(responseNotification);
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(apiResponse));
				}
				catch (ApiException e) {
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
				}
				catch (Exception e) {
					LOGGER.error("Failed to send Notification data.", e);
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

