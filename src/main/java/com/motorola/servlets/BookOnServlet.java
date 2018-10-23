/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.models.representation.ApiResponse;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.motorola.constants.InterfaceConstants.BOOK_ON_REQUEST_TYPE;

@WebServlet(urlPatterns = "/bookOn")
public class BookOnServlet extends BaseHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ValidationResult> validationResult = validateRequest(request, BOOK_ON_REQUEST_TYPE);
		if (validationResult.size() == 0) {
			UserSessionWrapper wrapper = translator.translateBookOn(payload);
			if (wrapper.getCorrelationId() != null) {
				//ApiResponse apiResponse = client.responseUserSessionCorrelationId(wrapper.getCorrelationId()).bookOnResponse(wrapper.getModel());
				//response.getOutputStream().write(apiResponse.toString().getBytes());
				//send also the model for reviewing on the interface side
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(wrapper);
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
