/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BaseRequestManager;
import com.motorola.manager.IncidentRequestManager;
import com.motorola.models.representation.EmergencyIncident;
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

@WebServlet(urlPatterns = "/incidentCreate")
public class IncidentCreateServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(IncidentCreateServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IncidentRequestManager requestManager = new IncidentRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.CREATE_INCIDENT_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			EmergencyIncident bean = requestManager.getTranslator().translateCreateIncident(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				try {
					requestManager.createIncident(bean);
				}
				catch (Exception e) {
					LOGGER.error("Failed to send createIncident data.", e);
				}
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(bean));
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

