/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.models.representation.AdditionalInfo;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Lookup;
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

import static com.motorola.constants.InterfaceConstants.ADDITIONAL_INFO_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.AGENCY_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.AREA_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.CALL_SIGN_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.CORERELATION_ID;
import static com.motorola.constants.InterfaceConstants.CUSTOMER_ID;
import static com.motorola.constants.InterfaceConstants.DATA_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.DEVICE_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.DISTRICT_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.ID_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.JURISDICTIONS_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.KEY_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.NEW_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.OLD_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.REQUEST_PARAMETERS;
import static com.motorola.constants.InterfaceConstants.REQUEST_TYPE;
import static com.motorola.constants.InterfaceConstants.SECTOR_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.SESSION_ID;
import static com.motorola.constants.InterfaceConstants.SHIFT_ID_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.STATION_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.TRUSTED_AGENCIES_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.UID_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.UNIT_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.USER_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.VEHICLE_ID_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.WHEN_SUBMITTED;
import static com.motorola.constants.InterfaceConstants.ZONE_JSON_KEY;

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
		String correlationId = utils.getStringByKey(payload, CORERELATION_ID);
		validateRequiredStringField(correlationId, CORERELATION_ID);
		result.setCorrelationId(correlationId);
		userSession.setCustomerId(utils.getStringByKey(payload, CUSTOMER_ID));
		userSession.setSessionId(utils.getStringByKey(payload, SESSION_ID));
		JsonObject requestParameters = utils.getJsonByKey(payload, REQUEST_PARAMETERS);
		if (validateRequiredObjectField(requestParameters, REQUEST_PARAMETERS)) {
			userSession.setUserId(utils.getStringFromNestedJsonByKey(requestParameters, USER_JSON_KEY, KEY_JSON_KEY));
		}
		userSession.setDeviceId(utils.getStringFromNestedJsonByKey(utils.getJsonByKey(payload, REQUEST_PARAMETERS), DEVICE_JSON_KEY, KEY_JSON_KEY));

		JsonObject requestParametersJSON = utils.getJsonByKey(payload, REQUEST_PARAMETERS);
		JsonObject additionInfoJSON = utils.getJsonByKey(requestParametersJSON, ADDITIONAL_INFO_JSON_KEY);
		JsonObject unitJSONObject = utils.getJsonByKey(additionInfoJSON, UNIT_JSON_KEY);
		AdditionalInfo additionalInfo = new AdditionalInfo();
		// gets the Unit info from addition object, translate to UserSession->AdditionalInfo->UnitHandle object fields
		UnitHandle unitHandler = new UnitHandle();
		unitHandler.setKey(utils.getStringByKey(unitJSONObject, KEY_JSON_KEY));
		unitHandler.setAgency(utils.getStringByKey(unitJSONObject, AGENCY_JSON_KEY));
		unitHandler.setCallSign(utils.getStringByKey(unitJSONObject, CALL_SIGN_JSON_KEY));
		unitHandler.setShiftId(unitJSONObject.get(SHIFT_ID_JSON_KEY).getAsString());
		additionalInfo.setUnit(unitHandler);
		// gets the Jurisdictions from addition object, translate to UserSession->AdditionalInfo-> List<Jurisdiction>
		additionalInfo.setJurisdictions(getJurisdictions(utils.getJsonArrayByKey(additionInfoJSON, JURISDICTIONS_JSON_KEY)));
		// get the district
		Lookup districtLookup = new Lookup();
		districtLookup.setUid(utils.getStringByKey(utils.getJsonByKey(additionInfoJSON, DISTRICT_JSON_KEY), UID_JSON_KEY));
		additionalInfo.setDistrict(districtLookup);
		// gets station
		Lookup stationLookup = new Lookup();
		stationLookup.setUid(utils.getStringByKey(utils.getJsonByKey(additionInfoJSON, STATION_JSON_KEY), UID_JSON_KEY));
		additionalInfo.setStation(stationLookup);
		additionalInfo.setVehicleId(utils.getStringByKey(additionInfoJSON, VEHICLE_ID_JSON_KEY));
		List<Lookup> trustedAgencies = getTrustedAgencies(additionInfoJSON);
		additionalInfo.setTrustedAgencies(trustedAgencies);
		userSession.setWhenSessionCreated(getCreationDate(payload));
		userSession.setAdditionalInfo(additionalInfo);
		result.setModel(userSession);

		return result;
	}

	@Override
	public ResponseNotification translateBookOff(JsonObject payload) {
		clearValidationResults();
		ResponseNotification result = new ResponseNotification();
		String correlationId = utils.getStringByKey(payload, CORERELATION_ID);
		validateRequiredStringField(correlationId, CORERELATION_ID);
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
	 * Gets the list of trusted agencies from json object
	 * @param infoJSON json to get the list of trusted agencies
	 * @return list with trusted agencies
	 */
	private List<Lookup> getTrustedAgencies(JsonObject infoJSON) {
		List<Lookup> result = new ArrayList<>();
		JsonArray agenciesJson = utils.getJsonArrayByKey(infoJSON, TRUSTED_AGENCIES_JSON_KEY);
		for (JsonElement element : agenciesJson) {
			JsonObject elementJSON = element.getAsJsonObject();
			Lookup trustedAgency = new Lookup();
			trustedAgency.setUid(utils.getStringByKey(elementJSON, UID_JSON_KEY));
			result.add(trustedAgency);
		}

		return result;
	}

	/**
	 * Gets the creation date from json object
	 * @param payload - json object
	 * @return
	 */
	private Date getCreationDate(JsonObject payload) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date result = null;
		String strDate = utils.getStringByKey(payload, WHEN_SUBMITTED);
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
	 * Gets the list og jurisdictions from incoming json
	 * @param jurisdictionsJSONArray - list of incoming jurisdictions
	 * @return the list of jurisdictions
	 */
	private List<Jurisdiction> getJurisdictions(JsonArray jurisdictionsJSONArray) {
		List<Jurisdiction> result = new ArrayList<>();
		for (JsonElement element : jurisdictionsJSONArray) {
			Lookup areaLookup = new Lookup();
			Lookup sectorLookup = new Lookup();
			Lookup zoneLookup = new Lookup();
			Jurisdiction jurisdiction = new Jurisdiction();
			JsonObject elementJSON = element.getAsJsonObject();
			JsonObject areaJSON = utils.getJsonByKey(elementJSON, AREA_JSON_KEY);
			JsonObject sectorJSON = utils.getJsonByKey(elementJSON, SECTOR_JSON_KEY);
			JsonObject zoneJSON = utils.getJsonByKey(elementJSON, ZONE_JSON_KEY);
			areaLookup.setUid(utils.getStringByKey(areaJSON, UID_JSON_KEY));
			sectorLookup.setUid(utils.getStringByKey(sectorJSON, UID_JSON_KEY));
			zoneLookup.setUid(utils.getStringByKey(zoneJSON, UID_JSON_KEY));
			jurisdiction.setArea(areaLookup);
			jurisdiction.setSector(sectorLookup);
			jurisdiction.setZone(zoneLookup);
			result.add(jurisdiction);
		}

		return result;
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
		if (field != null) {
			validationResults.add(new ValidationResult(String.format("%s is required field. ", fieldName), ValidationErrorType.MISSING_DATA));
			return false;
		}
		return true;
	}
}
