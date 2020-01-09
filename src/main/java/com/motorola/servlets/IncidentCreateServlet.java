/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiExceptionModel;
import com.motorola.api.utils.ExceptionModel;
import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.IncidentRequestManager;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.ModelApiResponse;
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
				ModelApiResponse modelApiResponse = null;
				try {
					modelApiResponse = requestManager.createIncident(bean);
				}
				catch (ApiException e) {
					respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(bean), CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
				}
				catch (Exception e) {
					LOGGER.error("Failed to send createIncident data.", e);
					respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(bean), CadCloudUtils.convertObjectToJsonString(new ExceptionModel(e.getMessage())));
				}
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(bean), CadCloudUtils.convertObjectToJsonString(modelApiResponse));
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

