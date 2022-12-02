/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.ApiExceptionModel;
import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BulkUpdateManager;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.RefreshUnitData;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/bulkUnitsUpdate")
public class BulkUnitsUpdateServlet extends BaseHttpServlet{

	private static final Logger LOGGER = LogManager.getLogger(BulkUnitsUpdateServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		BulkUpdateManager requestManager = new BulkUpdateManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.BULK_UNITS_UPDATE_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			RefreshUnitData refreshUnitData = requestManager.getTranslator().translateRefreshUnitData(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				ModelApiResponse modelApiResponse = null;
				try {
					modelApiResponse = requestManager.bulkUnitUpdate(refreshUnitData);
				}
				catch (ApiException e) {
					respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshUnitData), CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
				}
				catch (Exception e) {
					LOGGER.error("Failed to send bulk units update data.", e);
					respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshUnitData));
				}
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshUnitData), CadCloudUtils.convertObjectToJsonString(modelApiResponse));
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
