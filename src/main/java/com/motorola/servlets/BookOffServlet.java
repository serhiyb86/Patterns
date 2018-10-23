/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

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
		List<ValidationResult> validationResult = validateRequest(request, BOOK_OFF_REQUEST_TYPE);
		if (validationResult.size() == 0) {
			ResponseNotification responseNotification = translator.translateBookOff(payload);
			if (responseNotification.getCorrelationId() != null) {
				//ApiResponse apiResponse = client.responseNotification().responseNotification(responseNotification);
				//response.getOutputStream().write(apiResponse.toString().getBytes());
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(responseNotification);
				respondSuccess(response, outgoingModel);
			}
			else {
				respondFailure(response, translator.getValidationResults());
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}
}
