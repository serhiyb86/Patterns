/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.RefreshIncidentData;
import com.motorola.models.representation.RefreshUnitData;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.Unit;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.UpdateUnit;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.translation.v2019_1_15_0.mappers.incident.EmergencyIncidentMapper;
import com.motorola.translation.BaseTranslator;
import com.motorola.translation.v2019_1_15_0.mappers.bookon.UserSessionMapper;
import com.motorola.translation.v2019_1_15_0.mappers.incident.RefreshIncidentDataMapper;
import com.motorola.translation.v2019_1_15_0.mappers.notification.ResponseNotificationMapper;
import com.motorola.translation.v2019_1_15_0.mappers.unit.IncidentHandleMapper;
import com.motorola.translation.v2019_1_15_0.mappers.unit.RefreshUnitsDataMapper;
import com.motorola.translation.v2019_1_15_0.mappers.unit.UnitMapper;
import com.motorola.translation.v2019_1_15_0.mappers.unit.UpdateUnitMapper;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import com.motorola.validation.ValidationErrorType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Translator class for Spillman version 2019.1
 */
public class Translator implements BaseTranslator {

	private List<ValidationResult> validationResults = new ArrayList<>();

	@Override
	public UserSessionWrapper translateBookOn(JsonObject payload) {
		clearValidationResults();
		UserSessionWrapper result = new UserSessionWrapper();
		String correlationId = CadCloudUtils.getStringByKey(payload, InterfaceConstants.BookOnProperties.CORRELATION_ID);
		validateRequiredStringField(correlationId, InterfaceConstants.BookOnProperties.CORRELATION_ID);
		result.setCorrelationId(correlationId);
		UserSession userSession = new UserSessionMapper().createAndMapToUserSession(payload);
		result.setModel(userSession);
		validateRequiredObjectField(userSession.getApiAccessScope(), InterfaceConstants.BookOnProperties.API_ACCESS_SCOPE);

		return result;
	}

	@Override
	public ResponseNotification translateResponseNotification(JsonObject payload) {
		clearValidationResults();
		ResponseNotification notification = new ResponseNotificationMapper().createAndMapToResponseNotification(payload);
		validateRequiredStringField(notification.getCorrelationId(), InterfaceConstants.NotificationProperties.CORRELATION_ID);

		return notification;
	}

	@Override
	public EmergencyIncident translateCreateIncident(JsonObject payload) {
		clearValidationResults();
		EmergencyIncident emergencyIncident = null;
		JsonArray data = CadCloudUtils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY) && validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {
			JsonObject incident = data.get(0).getAsJsonObject();
			EmergencyIncidentMapper mapper = new EmergencyIncidentMapper();
			emergencyIncident = mapper.createAndMapToEmergencyIncident(incident);
		}
		return emergencyIncident;
	}

	@Override
	public UpdateEmergencyIncident translateUpdateIncident(JsonObject payload) {
		clearValidationResults();
		UpdateEmergencyIncident updateIncident = new UpdateEmergencyIncident();
		JsonArray data = CadCloudUtils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY)
			&& validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {

			JsonObject updateIncidentJson = data.get(0).getAsJsonObject();
			JsonObject old = CadCloudUtils.getJsonByKey(updateIncidentJson, InterfaceConstants.EmergencyIncident.GeneralProperties.OLD_JSON_KEY);
			JsonObject __new = CadCloudUtils.getJsonByKey(updateIncidentJson, InterfaceConstants.EmergencyIncident.GeneralProperties.NEW_JSON_KEY);
			if (validateRequiredObjectField(old, InterfaceConstants.EmergencyIncident.GeneralProperties.OLD_JSON_KEY)
				&& validateRequiredObjectField(__new, InterfaceConstants.EmergencyIncident.GeneralProperties.NEW_JSON_KEY)
				&& validateRequiredStringField(CadCloudUtils.getStringByKey(old, InterfaceConstants.EmergencyIncident.GeneralProperties.ID_JSON_KEY), InterfaceConstants.EmergencyIncident.GeneralProperties.ID_JSON_KEY)
				&& validateRequiredStringField(CadCloudUtils.getStringByKey(__new, InterfaceConstants.EmergencyIncident.GeneralProperties.ID_JSON_KEY),
				InterfaceConstants.EmergencyIncident.GeneralProperties.ID_JSON_KEY)) {

				EmergencyIncidentMapper mapper = new EmergencyIncidentMapper();
				EmergencyIncident newModel = mapper.createAndMapToEmergencyIncident(__new);
				EmergencyIncident oldModel = mapper.createAndMapToEmergencyIncident(old);

				updateIncident.set__new(newModel);
				updateIncident.setOld(oldModel);
			}
		}
		return updateIncident;
	}

	@Override
	public Unit translateUnitStatusCreate(JsonObject payload) {
		clearValidationResults();
		Unit unit = null;
		JsonArray data = CadCloudUtils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY) && validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {
			JsonObject unitData = data.get(0).getAsJsonObject();
			UnitMapper unitMapper = new UnitMapper();
			IncidentHandleMapper incidentHandleMapper = new IncidentHandleMapper();
			unit = unitMapper.createAndMapToUnit(unitData);
			unit.setAssignedIncident(incidentHandleMapper.createAndMapToIncidentHandle(unitData));
		}

		return unit;
	}

	@Override
	public UpdateUnit translateUnitStatusUpdates(JsonObject payload) {
		clearValidationResults();
		UpdateUnit updateUnit = null;
		JsonArray data = CadCloudUtils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY)
			&& validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {
			JsonObject updateUnitData = data.get(0).getAsJsonObject();
			UpdateUnitMapper mapper = new UpdateUnitMapper();
			updateUnit = mapper.createAndMapToUpdateUnit(updateUnitData);
		}
		return updateUnit;
	}

	/**
	 * Gets the list with validation result
	 * @return list with validation result
	 */
	public List<ValidationResult> getValidationResults() {
		return validationResults;
	}

	@Override
	public String getVersion() {
		return InterfaceConstants.GeneralProperties.VERSION_2019_1_15_0;
	}

	@Override
	 public RefreshUnitData translateRefreshUnitData(JsonObject payload) {
		clearValidationResults();
		RefreshUnitData refreshUnitData = null;
		JsonArray data = CadCloudUtils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY)
			&& validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {
			JsonObject refreshUnitDataJson = data.get(0).getAsJsonObject();
			RefreshUnitsDataMapper mapper = new RefreshUnitsDataMapper();
			refreshUnitData = mapper.createAndMapToRefreshUnitData(refreshUnitDataJson);
			refreshUnitData.setIsFirstBatchUpdate(true);
			refreshUnitData.setIsLastBatchUpdate(true);
		}
		return refreshUnitData;
	}

	@Override public RefreshIncidentData translateRefreshIncidentData(JsonObject payload) {
		clearValidationResults();
		RefreshIncidentData refreshIncidentData = null;
		JsonArray data = CadCloudUtils.getJsonArrayByKey(payload, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
		if (validateRequiredObjectField(data, InterfaceConstants.GeneralProperties.DATA_JSON_KEY)
			&& validateRequiredObjectField(data.get(0), InterfaceConstants.GeneralProperties.DATA_JSON_KEY)) {
			JsonObject refreshIncidentDataJson = data.get(0).getAsJsonObject();
			RefreshIncidentDataMapper mapper = new RefreshIncidentDataMapper();
			refreshIncidentData = mapper.createAndMapToRefreshIncidentData(refreshIncidentDataJson);
		}
		return refreshIncidentData;
	}

	/**
	 * Clears the list with validation result
	 */
	private void clearValidationResults() {
		this.validationResults = new ArrayList<>();
	}

	/**
	 * Checks if field is empty or null. If string is invalid adds error message to the validationResults list
	 * @param field to validate
	 * @param fieldName name of the field
	 * @return true if present, false if not
	 */
	private boolean validateRequiredStringField(String field, String fieldName) {
		if (StringUtils.isBlank(field)) {
			validationResults.add(new ValidationResult(String.format("%s is required field. ", fieldName), ValidationErrorType.MISSING_DATA));
			return false;
		}
		return true;
	}

	/**
	 * Checks if field is null. If so adds error message to the validationResults list
	 * @param field to validate
	 * @param fieldName name of the field
	 * @return true if present, false if not
	 */
	private boolean validateRequiredObjectField(Object field, String fieldName) {
		if (field == null) {
			validationResults.add(new ValidationResult(String.format("%s is required field. ", fieldName), ValidationErrorType.MISSING_DATA));
			return false;
		}
		return true;
	}
}
