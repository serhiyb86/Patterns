/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.AdditionalInfo;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.UnitHandle;
import com.motorola.models.representation.UserSession;
import com.motorola.models.representation.UserSessionWrapper;
import com.motorola.translation.BaseTranslator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Translator class for Spillman version 2019.1
 */
public class Translator2019_1 implements BaseTranslator {

	private static final Logger LOGGER = LoggerFactory.getLogger(Translator2019_1.class);

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
	 * @param fieldK0ey - field key
	 * @return field string value if exist, otherwise - null
	 */
	private String getStringFromNestedJsoByKey(JsonObject rootJson, String nestedJsonKey, String fieldK0ey) {
		String result = null;
		if (rootJson != null && rootJson.get(nestedJsonKey) != null) {
			JsonObject nestedObject = rootJson.get(nestedJsonKey).getAsJsonObject();
			if (nestedObject.get(fieldK0ey) != null) {
				result = nestedObject.get(fieldK0ey).getAsString();
			}
			else {
				LOGGER.debug("the nested json %s does not contain key  %s", nestedJsonKey, fieldK0ey);
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
			LOGGER.debug("the json does not contain key  %s for string value", key);
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

	@Override
	public UserSessionWrapper translateBookOn(JsonObject payload) {
		UserSessionWrapper result = new UserSessionWrapper();
		UserSession userSession = new UserSession();
		result.setCorrelationId(getStringByKey(payload, InterfaceConstants.CORERELATION_ID));
		userSession.setCustomerId(getStringByKey(payload, InterfaceConstants.CUSTOMER_ID));
		userSession.setSessionId(getStringByKey(payload, InterfaceConstants.SESSION_ID));
		userSession.setUserId(getStringFromNestedJsoByKey(getJsonByKey(payload, InterfaceConstants.REQUEST_PARAMETERS), InterfaceConstants.USER_JSON_KEY, InterfaceConstants.JSON_KEY));
		userSession.setDeviceId(getStringFromNestedJsoByKey(getJsonByKey(payload, InterfaceConstants.REQUEST_PARAMETERS), InterfaceConstants.DEVICE_JSON_KEY, InterfaceConstants.JSON_KEY));

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

	@Override public ResponseNotification translateBookOff(JsonObject payload) {
		//TODO translation logic must be here
		return new ResponseNotification();
	}
}
