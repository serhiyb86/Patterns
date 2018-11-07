/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.manager.BaseRequestManager;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.motorola.constants.InterfaceConstants.BOOK_OFF_REQUEST_TYPE;

@WebServlet(urlPatterns = "/bookOff")
public class BookOffServlet extends BaseHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseRequestManager requestManager = new BaseRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, BOOK_OFF_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			ResponseNotification responseNotification = requestManager.getTranslator().translateBookOff(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				//ApiResponse apiResponse = client.responseNotification().responseNotification(responseNotification);
				//response.getOutputStream().write(apiResponse.toString().getBytes());
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(responseNotification);
				respondSuccess(response, outgoingModel);
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
