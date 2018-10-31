/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.models.representation.AdditionalInfo;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.MonitorAreas;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.UnitHandle;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.translation.BaseTranslator;
import com.motorola.utils.CadCloudUtils;
import com.motorola.validation.ValidationResult;
import com.motorola.validation.ValidationErrorType;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.motorola.constants.InterfaceConstants.*;

/**
 * Translator class for Spillman version 2019.1
 */
public class Translator2019_1 implements BaseTranslator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Translator2019_1.class);
	private List<ValidationResult> validationResults = new ArrayList<>();
	private final CadCloudUtils utils = new CadCloudUtils();

	@Override
	public UserSessionWrapper translateBookOn(JsonObject payload) {
		clearValidationResults();
		UserSessionWrapper result = new UserSessionWrapper();
		UserSession userSession = new UserSession();
		String correlationId = utils.getStringByKey(payload, CORRELATION_ID);
		validateRequiredStringField(correlationId, CORRELATION_ID);
		result.setCorrelationId(correlationId);
		userSession.setCustomerId(utils.getStringByKey(payload, CUSTOMER_ID));
		userSession.setSessionId(utils.getStringByKey(payload, SESSION_ID));
		userSession.setDeviceId(utils.getStringByKey(payload, DEVICE_ID));
		userSession.setUserId(utils.getStringByKey(payload, USER_ID));
		userSession.setWhenSessionCreated(getDateByKey(payload, WHEN_SESSION_CREATED));
		userSession.setWhenSessionUpdated(getDateByKey(payload, WHEN_SESSION_UPDATED));
		userSession.setRoleKey(ROLE_KEY_VAL);
		if (validateRequiredObjectField(payload, API_ACCESS_LIST)) {
			JsonArray apiAccessList = utils.getJsonArrayByKey(payload, API_ACCESS_LIST);
			userSession.setApiAccessList(getAccessList(apiAccessList));
		}
		if (validateRequiredObjectField(payload, MONITOR_AREAS)) {
			JsonObject monitorArea = utils.getJsonByKey(payload, MONITOR_AREAS);
			userSession.setMonitorAreas(getMonitorArea(monitorArea));
		}

		if (validateRequiredObjectField(payload, ADDITIONAL_INFO_JSON_KEY)) {
			JsonObject additionInfoJSON = utils.getJsonByKey(payload, ADDITIONAL_INFO_JSON_KEY);
			userSession.setAdditionalInfo(getAdditionInfo(additionInfoJSON));
		}

		result.setModel(userSession);
		return result;
	}

	/**
	 * Gets the additional information from incoming json
	 * @param additionInfoJSON - incoming json payload
	 * @return - addition information object
	 */
	private AdditionalInfo getAdditionInfo(JsonObject additionInfoJSON) {
		AdditionalInfo result = new AdditionalInfo();
		JsonObject unitJSONObject = utils.getJsonByKey(additionInfoJSON, UNIT_JSON_KEY);
		UnitHandle unitHandler = new UnitHandle();
		unitHandler.setKey(utils.getStringByKey(unitJSONObject, KEY_JSON_KEY));
		unitHandler.setAgency(utils.getStringByKey(unitJSONObject, AGENCY_JSON_KEY));
		result.setUnit(unitHandler);
		return result;
	}

	/**
	 * Gets the monitor areas information
	 * @param monitorArea - json payload with monitor area information
	 * @return
	 */
	private MonitorAreas getMonitorArea(JsonObject monitorArea) {
		MonitorAreas result = new MonitorAreas();
		result.setAreaKeys(createLooup(utils.getJsonArrayByKey(monitorArea, AREAS)));
		return result;
	}

	/**
	 * Creates the  list of lookup from jsonarry elements uid
	 * @param array json azrra with values
	 * @return the list of lookups
	 */
	private List<Lookup> createLooup(JsonArray array) {
		List<Lookup> result = new ArrayList<>();
		for (JsonElement element : array) {
			JsonObject areaJSON = element.getAsJsonObject();
			Lookup areaLookup = new Lookup();
			areaLookup.setUid(utils.getStringByKey(areaJSON, UID_JSON_KEY));
			result.add(areaLookup);
		}

		return result;
	}

	private List<String> getAccessList(JsonArray apiAccessList) {
		List<String> result = new ArrayList<>();
		for (JsonElement element : apiAccessList) {
			JsonObject permissionJSON = element.getAsJsonObject();
			result.add(utils.getStringByKey(permissionJSON, PERMISSION_ID));
		}

		return result;
	}

	@Override
	public ResponseNotification translateBookOff(JsonObject payload) {
		clearValidationResults();
		ResponseNotification result = new ResponseNotification();
		String correlationId = utils.getStringByKey(payload, CORRELATION_ID);
		validateRequiredStringField(correlationId, CORRELATION_ID);
		result.setCorrelationId(correlationId);
		result.setCustomerId(utils.getStringByKey(payload, CUSTOMER_ID));
		result.setSessionId(utils.getStringByKey(payload, SESSION_ID));
		result.setResponseType(utils.getStringByKey(payload, REQUEST_TYPE));

		return result;
	}

	@Override
	public EmergencyIncident translateCreateIncident(JsonObject payload) {
		clearValidationResults();
		EmergencyIncident emergencyIncident = new EmergencyIncident();
		JsonArray data = utils.getJsonArrayByKey(payload, DATA_JSON_KEY);
		if (validateRequiredObjectField(data, DATA_JSON_KEY) && validateRequiredObjectField(data.get(0), DATA_JSON_KEY)) {
			JsonObject incident = data.get(0).getAsJsonObject();
			String id = utils.getStringByKey(incident, ID_JSON_KEY);
			if (validateRequiredStringField(id, ID_JSON_KEY)) {
				emergencyIncident.setId(id);
				emergencyIncident.setKey(id);
			}
		}

		return emergencyIncident;
	}

	@Override
	public UpdateEmergencyIncident translateUpdateIncident(JsonObject payload) {
		clearValidationResults();
		UpdateEmergencyIncident updateIncident = new UpdateEmergencyIncident();
		JsonArray data = utils.getJsonArrayByKey(payload, DATA_JSON_KEY);
		if (validateRequiredObjectField(data, DATA_JSON_KEY) && validateRequiredObjectField(data.get(0), DATA_JSON_KEY)) {
			JsonObject updateIncidentJson = data.get(0).getAsJsonObject();
			JsonObject old = utils.getJsonByKey(updateIncidentJson, OLD_JSON_KEY);
			JsonObject __new = utils.getJsonByKey(updateIncidentJson, NEW_JSON_KEY);
			if (validateRequiredObjectField(old, OLD_JSON_KEY) && validateRequiredObjectField(__new, NEW_JSON_KEY)
				&& validateRequiredStringField(utils.getStringByKey(old, ID_JSON_KEY), ID_JSON_KEY)
				&& validateRequiredStringField(utils.getStringByKey(__new, ID_JSON_KEY), ID_JSON_KEY)) {

				EmergencyIncident newModel = new EmergencyIncident();
				String new_id = utils.getStringByKey(__new, ID_JSON_KEY);
				newModel.setKey(new_id);
				newModel.setId(new_id);

				EmergencyIncident oldModel = new EmergencyIncident();
				String old_id = utils.getStringByKey(old, ID_JSON_KEY);
				oldModel.setId(old_id);
				oldModel.setKey(old_id);

				updateIncident.set__new(newModel);
				updateIncident.setOld(oldModel);
			}
		}

		return updateIncident;
	}

	/**
	 * Gets the date from json object
	 * @param payload - json object
	 * @return
	 */
	private Date getDateByKey(JsonObject payload, String key) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date result = null;
		String strDate = utils.getStringByKey(payload, key);
		if (strDate != null) {
			try {
				result = formatter.parse(strDate);
			}
			catch (ParseException e) {
				LOGGER.error("Failed to parse submitted date.");
			}

		}

		return result;
	}

	/**
	 * Create the looup objects from uid values
	 * @param json
	 * @param key
	 * @return
	 */
	private Lookup createLookup(JsonObject json, String key) {
		JsonObject uidJson = utils.getJsonByKey(json, key);
		Lookup lookup = new Lookup();
		lookup.setUid(utils.getStringByKey(uidJson, UID_JSON_KEY));
		return lookup;
	}

	/**
	 * Gets the list with validation result
	 * @return list with validation result
	 */
	public List<ValidationResult> getValidationResults() {
		return validationResults;
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
		if (StringUtils.isNullOrEmpty(field)) {
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
