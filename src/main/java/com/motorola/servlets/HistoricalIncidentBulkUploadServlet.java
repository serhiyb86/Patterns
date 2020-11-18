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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = "/historicalIncidentBulkUpload")
public class HistoricalIncidentBulkUploadServlet extends BaseHttpServlet{

	private static final Logger LOGGER = LoggerFactory.getLogger(BulkIncidentsUpdate.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		BulkUpdateManager requestManager = new BulkUpdateManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.HISTORICAL_INCIDENT_BULK_UPLOAD_TYPE);
		if (validationResult.isEmpty()) {
			RefreshIncidentData refreshIncidentData = requestManager.getTranslator().translateRefreshIncidentData(requestManager.getPayload());
			if (!refreshIncidentData.getEmergencyIncidentList().isEmpty()) {
				if (requestManager.getTranslator().getValidationResults().isEmpty()) {
					ModelApiResponse modelApiResponse = null;
					try {
						modelApiResponse = requestManager.bulkHistoricalIncidentUpdate(refreshIncidentData);
					} catch (ApiException e) {
						respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(e)));
					} catch (Exception e) {
						LOGGER.error("Failed to send bulk historical incidents update data.", e);
						respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(new ExceptionModel(e.getMessage())));
					}
					respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(modelApiResponse));
				}
				else {
					respondFailure(response, requestManager.getTranslator().getValidationResults());
				}
			}
			else {
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(refreshIncidentData), CadCloudUtils.convertObjectToJsonString(new ApiExceptionModel(new ApiException(400, "All incidents are scheduled and weren't sent to the cloud", null, "All incidents are scheduled and weren't sent to the cloud"))));
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}
}
