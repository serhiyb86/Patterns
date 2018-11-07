/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

	import com.motorola.manager.BaseRequestManager;
	import com.motorola.models.representation.EmergencyIncident;
	import com.motorola.utils.CadCloudUtils;
	import com.motorola.validation.ValidationResult;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import java.io.IOException;
	import java.util.List;

	import static com.motorola.constants.InterfaceConstants.CREATE_INCIDENT_REQUEST_TYPE;

@WebServlet(urlPatterns = "/incidentCreate")
public class IncidentCreateServlet extends BaseHttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BaseRequestManager requestManager = new BaseRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, CREATE_INCIDENT_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			EmergencyIncident bean = requestManager.getTranslator().translateCreateIncident(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				//client.pushIncident().createIncident(bean);
				String outgoingModel = CadCloudUtils.convertObjectToJsonString(bean);
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

