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
import com.motorola.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/bookOff")
public class BookOffServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LogManager.getLogger(BookOffServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookOnOffRequestManager requestManager = new BookOnOffRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.NotificationProperties.BOOK_OFF_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			ResponseNotification responseNotification = requestManager.getTranslator().translateResponseNotification(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(responseNotification);
				try {
					ModelApiResponse apiResponse = requestManager.bookOff(responseNotification);
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(apiResponse));
				}
				catch (ApiException e) {
					respondWithTranslatedModel(response, outgoingModel, CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
				}
				catch (Exception e) {
					LOGGER.error("Failed to send responseNotification data.", e);
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
