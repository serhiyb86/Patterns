/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
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
import com.motorola.validation.ValidationResult;
import com.motorola.validation.ValidationType;
import org.restlet.engine.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.motorola.constants.InterfaceConstants.DATA_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.ID_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.NEW_JSON_KEY;
import static com.motorola.constants.InterfaceConstants.OLD_JSON_KEY;

/**
 * Translator class for Spillman version 2019.1
 */
public class Translator2019_1 implements BaseTranslator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Translator2019_1.class);
	private List<ValidationResult> validationResults = new ArrayList<>();

	@Override
	public UserSessionWrapper translateBookOn(JsonObject payload) {
		clearValidationResults();
		UserSessionWrapper result = new UserSessionWrapper();
		UserSession userSession = new UserSession();
		result.setCorrelationId(getStringByKey(payload, InterfaceConstants.CORERELATION_ID));
		userSession.setCustomerId(getStringByKey(payload, InterfaceConstants.CUSTOMER_ID));
		userSession.setSessionId(getStringByKey(payload, InterfaceConstants.SESSION_ID));
		userSession.setUserId(getStringFromNestedJsonByKey(getJsonByKey(payload, InterfaceConstants.REQUEST_PARAMETERS), InterfaceConstants.USER_JSON_KEY, InterfaceConstants.JSON_KEY));
		userSession.setDeviceId(getStringFromNestedJsonByKey(getJsonByKey(payload, InterfaceConstants.REQUEST_PARAMETERS), InterfaceConstants.DEVICE_JSON_KEY, InterfaceConstants.JSON_KEY));

		JsonObject requestParametersJSON = getJsonByKey(payload, InterfaceConstants.REQUEST_PARAMETERS);
		JsonObject additionInfoJSON = getJsonByKey(requestParametersJSON, InterfaceConstants.ADDITIONAL_INFO_JSON_KEY);
		JsonObject unitJSONObject = getJsonByKey(additionInfoJSON, InterfaceConstants.UNIT_JSON_KEY);
		AdditionalInfo additionalInfo = new AdditionalInfo();
		// gets the Unit info from addition object, translate to UserSession->AdditionalInfo->UnitHandle object fields
		UnitHandle unitHandler = new UnitHandle();
		unitHandler.setKey(getStringByKey(unitJSONObject, InterfaceConstants.JSON_KEY));
		unitHandler.setAgency(getStringByKey(unitJSONObject, InterfaceConstants.AGENCY_JSON_KEY));
		unitHandler.setCallSign(getStringByKey(unitJSONObject, InterfaceConstants.CALL_SIGN_JSON_KEY));
		unitHandler.setShiftId(unitJSONObject.get(InterfaceConstants.SHIFT_ID_JSON_KEY).getAsString());
		additionalInfo.setUnit(unitHandler);
		// gets the Jurisdictions from addition object, translate to UserSession->AdditionalInfo-> List<Jurisdiction>
		additionalInfo.setJurisdictions(getJurisdictions(getJsonArrayByKey(additionInfoJSON, InterfaceConstants.JURISDICTIONS_JSON_KEY)));
		// get the district
		Lookup districtLookup = new Lookup();
		districtLookup.setUid(getStringByKey(getJsonByKey(additionInfoJSON, InterfaceConstants.DISTRICT_JSON_KEY), InterfaceConstants.UID_JSON_KEY));
		additionalInfo.setDistrict(districtLookup);
		// gets station
		Lookup stationLookup = new Lookup();
		stationLookup.setUid(getStringByKey(getJsonByKey(additionInfoJSON, InterfaceConstants.STATION_JSON_KEY), InterfaceConstants.UID_JSON_KEY));
		additionalInfo.setStation(stationLookup);
		// transform vehicle
		additionalInfo.setVehicleId(getStringByKey(additionInfoJSON, InterfaceConstants.VEHICLE_ID_JSON_KEY));
		// get trusted agencies
		List<Lookup> trustedAgencies = getTrustedAgencies(additionInfoJSON);
		additionalInfo.setTrustedAgencies(trustedAgencies);
		// transform creation date
		userSession.setWhenSessionCreated(getCreationDate(payload));
		// transform request parameters
		userSession.setAdditionalInfo(additionalInfo);
		result.setModel(userSession);

		return result;
	}

	@Override
	public ResponseNotification translateBookOff(JsonObject payload) {
		clearValidationResults();
		ResponseNotification result = new ResponseNotification();
		// translate correlation id
		result.setCorrelationId(getStringByKey(payload, InterfaceConstants.CORERELATION_ID));
		// translate customer id
		result.setCustomerId(getStringByKey(payload, InterfaceConstants.CUSTOMER_ID));
		// translate session ID
		result.setSessionId(getStringByKey(payload, InterfaceConstants.SESSION_ID));
		// translate request type
		result.setResponseType(getStringByKey(payload, InterfaceConstants.REQUEST_TYPE));

		return result;
	}

	@Override
	public EmergencyIncident translateCreateIncident(JsonObject payload) {
		clearValidationResults();
		EmergencyIncident emergencyIncident = new EmergencyIncident();
		JsonArray data = getJsonArrayByKey(payload, DATA_JSON_KEY);
		if (data != null && data.get(0) != null) {
			JsonObject incident = data.get(0).getAsJsonObject();
			String id = getStringByKey(incident, ID_JSON_KEY);
			if (StringUtils.isNullOrEmpty(id)) {
				validationResults.add(new ValidationResult("Incident id is missing.", ValidationType.MISSING_DATA));
			}
			else {
				emergencyIncident.setId(id);
				emergencyIncident.setKey(id);
			}
		}
		else {
			validationResults.add(new ValidationResult("Payload data is missing or empty.", ValidationType.MISSING_DATA));
		}

		return emergencyIncident;
	}

	@Override
	public UpdateEmergencyIncident translateUpdateIncident(JsonObject payload) {
		clearValidationResults();
		UpdateEmergencyIncident updateIncident = new UpdateEmergencyIncident();

		JsonArray data = getJsonArrayByKey(payload, DATA_JSON_KEY);
		if (data != null && data.get(0) != null) {
			JsonObject updateIncidentJson = data.get(0).getAsJsonObject();
			JsonObject old = getJsonByKey(updateIncidentJson, OLD_JSON_KEY);
			JsonObject __new = getJsonByKey(updateIncidentJson, NEW_JSON_KEY);

			if (old != null && __new != null && !StringUtils.isNullOrEmpty(getStringByKey(old, ID_JSON_KEY))
				&& !StringUtils.isNullOrEmpty(getStringByKey(__new, ID_JSON_KEY))) {

				EmergencyIncident newModel = new EmergencyIncident();
				String new_id = getStringByKey(__new, ID_JSON_KEY);
				newModel.setKey(new_id);
				newModel.setId(new_id);

				EmergencyIncident oldModel = new EmergencyIncident();
				String old_id = getStringByKey(old, ID_JSON_KEY);
				oldModel.setId(old_id);
				oldModel.setKey(old_id);

				updateIncident.set__new(newModel);
				updateIncident.setOld(oldModel);
			}
			else {
				if (old == null) {
					validationResults.add(new ValidationResult("Old model is missing or empty.", ValidationType.MISSING_DATA));
				}

				if (__new == null) {
					validationResults.add(new ValidationResult("New model is missing or empty.", ValidationType.MISSING_DATA));
				}
			}
		}
		else {
			validationResults.add(new ValidationResult("Payload data is missing or empty.", ValidationType.MISSING_DATA));
		}

		return updateIncident;
	}

	private List<Lookup> getTrustedAgencies(JsonObject infoJSON) {
		List<Lookup> result = new ArrayList<>();
		JsonArray agenciesJson = getJsonArrayByKey(infoJSON, InterfaceConstants.TRUSTED_AGENCIES_JSON_KEY);
		for (JsonElement element : agenciesJson) {
			JsonObject elementJSON = element.getAsJsonObject();
			Lookup trustedAgency = new Lookup();
			trustedAgency.setUid(getStringByKey(elementJSON, InterfaceConstants.UID_JSON_KEY));
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
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		Date result = null;
		String strDate = getStringByKey(payload, InterfaceConstants.WHEN_SUBMITTED);
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
			JsonObject areaJSON = getJsonByKey(elementJSON, InterfaceConstants.AREA_JSON_KEY);
			JsonObject sectorJSON = getJsonByKey(elementJSON, InterfaceConstants.SECTOR_JSON_KEY);
			JsonObject zoneJSON = getJsonByKey(elementJSON, InterfaceConstants.ZONE_JSON_KEY);
			areaLookup.setUid(getStringByKey(areaJSON, InterfaceConstants.UID_JSON_KEY));
			sectorLookup.setUid(getStringByKey(sectorJSON, InterfaceConstants.UID_JSON_KEY));
			zoneLookup.setUid(getStringByKey(zoneJSON, InterfaceConstants.UID_JSON_KEY));
			jurisdiction.setArea(areaLookup);
			jurisdiction.setSector(sectorLookup);
			jurisdiction.setZone(zoneLookup);
			result.add(jurisdiction);
		}

		return result;
	}

	/**
	 * Get the value of field inside nested json by key
	 * @param rootJson root json object
	 * @param nestedJsonKey - nested json key
	 * @param fieldKey - field key
	 * @return field string value if exist, otherwise - null
	 */
	private String getStringFromNestedJsonByKey(JsonObject rootJson, String nestedJsonKey, String fieldKey) {
		String result = null;
		if (rootJson != null && rootJson.get(nestedJsonKey) != null) {
			JsonObject nestedObject = rootJson.get(nestedJsonKey).getAsJsonObject();
			if (nestedObject.get(fieldKey) != null) {
				result = nestedObject.get(fieldKey).getAsString();
			}
			else {
				LOGGER.debug("the nested json %s does not contain key  %s", nestedJsonKey, fieldKey);
			}
		}
		else {
			LOGGER.debug("the root json does not contain key  %s", nestedJsonKey);
		}
		return result;
	}

	/**
	 * Gets the String value from incoming json
	 * @param json - json payload object
	 * @param key - the property key
	 * @return - the value if exist, otherwise - null
	 */
	private String getStringByKey(JsonObject json, String key) {
		String result = null;
		if (json != null && json.get(key) != null) {
			result = json.get(key).getAsString();
		}
		else {
			LOGGER.debug("The json does not contain key  %s for string value", key);
		}
		return result;
	}

	/**
	 * Gets JSON value from incoming json
	 * @param json - json payload object
	 * @param key - the property key
	 * @return - the value if exist, otherwise - null
	 */
	private JsonObject getJsonByKey(JsonObject json, String key) {
		JsonObject result = null;
		if (json != null && json.get(key) != null) {
			result = json.get(key).getAsJsonObject();
		}
		else {
			LOGGER.debug("the json does not contain key  %s for json value", key);
		}
		return result;
	}

	private JsonArray getJsonArrayByKey(JsonObject json, String key) {
		JsonArray result = null;
		if (json != null && json.get(key) != null) {
			result = json.get(key).getAsJsonArray();
		}
		else {
			LOGGER.debug("the json does not contain key  %s for json array value", key);
		}
		return result;
	}

	public List<ValidationResult> getValidationResults() {
		return validationResults;
	}

	private void clearValidationResults() {
		this.validationResults = new ArrayList<>();
	}
}
