/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.servlets;

import com.motorola.constants.InterfaceConstants;
import com.motorola.manager.BaseRequestManager;
import com.motorola.manager.UnitRequestManager;
import com.motorola.models.representation.Unit;
import com.motorola.models.representation.UpdateUnit;
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

@WebServlet(urlPatterns = "/unitStatus")
public class UnitStatusUpdatesServlet extends BaseHttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(UnitStatusUpdatesServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UnitRequestManager requestManager = new UnitRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.UNIT_STATUS_CREATE_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			Unit unit = requestManager.getTranslator().translateUnitStatusCreate(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				try {
					requestManager.onDutyUnit(unit);
				}
				catch (Exception e) {
					LOGGER.error("Failed to send onDutyUnit data.", e);
				}
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(unit));
			}
			else {
				respondFailure(response, requestManager.getTranslator().getValidationResults());
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) {
		UnitRequestManager requestManager = new UnitRequestManager();
		List<ValidationResult> validationResult = requestManager.validateRequest(request, InterfaceConstants.EmergencyIncident.GeneralProperties.UNIT_STATUS_UPDATE_REQUEST_TYPE);
		if (validationResult.isEmpty()) {
			UpdateUnit unitUpdates = requestManager.getTranslator().translateUnitStatusUpdates(requestManager.getPayload());
			if (requestManager.getTranslator().getValidationResults().isEmpty()) {
				try {
					requestManager.unitStatusUpdates(unitUpdates);
				}
				catch (Exception e) {
					LOGGER.error("Failed to send unitStatusUpdates data.", e);
				}
				respondWithTranslatedModel(response, CadCloudUtils.convertObjectToJsonString(unitUpdates));
			}
			else {
				respondFailure(response, requestManager.getTranslator().getValidationResults());
			}
		}
		else {
			respondFailure(response, validationResult);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
		//TODO: implement delete endpoint when needed
	}
}
