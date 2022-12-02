/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiExceptionModel;
import com.motorola.api.utils.ExceptionModel;
import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BulkUpdateManager;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.RefreshIncidentData;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/bulkIncidentsUpdate")
public class BulkIncidentsUpdate extends BaseHttpServlet{

	private static final Logger LOGGER = LogManager.getLogger(BulkIncidentsUpdate.class);
	private static final int BAD_REQUEST = 400;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		BulkUpdateManager requestManager = new BulkUpdateManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.BULK_INCIDENTS_UPDATE_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			RefreshIncidentData refreshIncidentData = requestManager.getTranslator().translateRefreshIncidentData(requestManager.getPayload());
			CadCloudUtils.excludeScheduledIncidentsFromBulk(refreshIncidentData);
			if (!refreshIncidentData.getEmergencyIncidentList().isEmpty()) {
				if (requestManager.getTranslator().getValidationResults().isEmpty()) {
					ModelApiResponse modelApiResponse = null;
					try {
						modelApiResponse = requestManager.bulkIncidentUpdate(refreshIncidentData);
					}
					catch (ApiException e) {
						respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
					}
					catch (Exception e) {
						LOGGER.error("Failed to send bulk incidents update data.", e);
						respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(new ExceptionModel(e.getMessage())));
					}
					respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(modelApiResponse));
				}
				else {
					respondFailure(response, requestManager.getTranslator().getValidationResults());
				}
			}
			else {
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(new ApiException(BAD_REQUEST, "All incidents are scheduled and weren't sent to the cloud", null, "All incidents are scheduled and weren't sent to the cloud"))));
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}

}