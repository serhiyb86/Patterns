/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BaseRequestManager;
import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/bookOff")
public class BookOffServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookOffServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseRequestManager requestManager = new BaseRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.NotificationProperties.BOOK_OFF_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			ResponseNotification responseNotification = requestManager.getTranslator().translateResponseNotification(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(responseNotification);
				ServletOutputStream outputStream = null;
				try {
					ApiResponse apiResponse = requestManager.getApiClient().responseNotification().responseNotification(responseNotification);
					outputStream = response.getOutputStream();
					outputStream.write(apiResponse.toString().getBytes());
				}
				catch (Exception e) {
					LOGGER.error("Failed to send responseNotification data.", e);
					respondWithTranslatedModel(response, outgoingModel);
				}
				finally {
					if (outputStream != null) {
						outputStream.close();
					}
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
