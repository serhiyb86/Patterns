/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BaseRequestManager;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/bookOn")
public class BookOnServlet extends BaseHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseRequestManager requestManager = new BaseRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.BookOnProperties.BOOK_ON_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			UserSessionWrapper wrapper = requestManager.getTranslator().translateBookOn(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				//ApiResponse apiResponse = client.responseUserSessionCorrelationId(wrapper.getCorrelationId()).bookOnResponse(wrapper.getModel());
				//response.getOutputStream().write(apiResponse.toString().getBytes());
				//send also the model for reviewing on the interface side
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(wrapper);
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

