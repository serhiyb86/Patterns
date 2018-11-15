/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BaseRequestManager;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/incidentUpdate")
public class IncidentUpdateServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(IncidentUpdateServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseRequestManager requestManager = new BaseRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.UPDATE_INCIDENT_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			UpdateEmergencyIncident bean = requestManager.getTranslator().translateUpdateIncident(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(bean);
				try {
					requestManager.getApiClient().pushIncident().updateIncident(bean);
				}
				catch (Exception e) {
					LOGGER.error("Failed to send updateIncident data.", e);
					respondWithTranslatedModel(response, outgoingModel);
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

