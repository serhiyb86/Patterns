/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.motorola.constants.InterfaceConstants.UPDATE_INCIDENT_REQUEST_TYPE;

@WebServlet(urlPatterns = "/incidentUpdate")
public class IncidentUpdateServlet extends BaseHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<ValidationResult> validationResult = validateRequest(request, UPDATE_INCIDENT_REQUEST_TYPE);
			if (validationResult.isEmpty()) {
				UpdateEmergencyIncident bean = translator.translateUpdateIncident(payload);
				if (translator.getValidationResults().isEmpty()) {
					//client.pushIncident().updateIncident(bean);
					String outgoingModel = CadCloudUtils.convertObjectToJsonString(bean);
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
		finally {
			clearResources();
		}
	}
}
