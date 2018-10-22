/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.AdditionalInfo;
import com.motorola.models.representation.BookOffParameters;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Lookup;
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

	@Override
	public UserSessionWrapper translateBookOn(JsonObject payload) {
		UserSessionWrapper result = new UserSessionWrapper();
		// read the correlationId from incoming json
		if (payload.get(InterfaceConstants.CORERELATION_ID) != null) {
			result.setCorrelationId(payload.get(InterfaceConstants.CORERELATION_ID).getAsString());
		}

		UserSession userSession = new UserSession();
		// transform customer_id
		if (payload.get(InterfaceConstants.CUSTOMER_ID) != null) {
			userSession.setCustomerId(payload.get(InterfaceConstants.CUSTOMER_ID).getAsString());
		}

		// transform session_id
		if (payload.get(InterfaceConstants.SESSION_ID) != null) {
			userSession.setSessionId(payload.get(InterfaceConstants.SESSION_ID).getAsString());
		}
		// transform creation date
		if (payload.get(InterfaceConstants.WHEN_SUBMITTED) != null) {
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
				String submitDateStr = payload.get(InterfaceConstants.WHEN_SUBMITTED).getAsString();
				Date submitDate = formatter.parse(submitDateStr);
				userSession.setWhenSessionCreated(submitDate);
			}
			catch (ParseException e) {
				LOGGER.error("Failed to parse submitted date.");
			}
		}

		// transform request parameters
		if (payload.get(InterfaceConstants.REQUEST_PARAMETERS) != null) {
			JsonObject requestParameters = payload.get(InterfaceConstants.REQUEST_PARAMETERS).getAsJsonObject();
			// parse and transform userID, using user JSON object
			if (requestParameters.get(InterfaceConstants.USER_JSON_KEY) != null) {
				JsonObject userJSONObject = requestParameters.get(InterfaceConstants.USER_JSON_KEY).getAsJsonObject();
				userSession.setUserId(userJSONObject.get(InterfaceConstants.JSON_KEY).getAsString());
			}

			// parse and transform devicesID
			if (requestParameters.get(InterfaceConstants.DEVICE_JSON_KEY) != null) {
				JsonObject deviceJSONObject = requestParameters.get(InterfaceConstants.DEVICE_JSON_KEY).getAsJsonObject();
				userSession.setDeviceId(deviceJSONObject.get(InterfaceConstants.JSON_KEY).getAsString());
			}

			// parse and translate addition information
			if (requestParameters.get(InterfaceConstants.ADDITIONAL_INFO_JSON_KEY) != null) {
				AdditionalInfo additionalInfo = new AdditionalInfo();
				JsonObject additionInfoJSONObject = requestParameters.get(InterfaceConstants.ADDITIONAL_INFO_JSON_KEY).getAsJsonObject();
				// gets the Unit info from addition object, translate to UserSession->AdditionalInfo->UnitHandle object fields
				if (additionInfoJSONObject.get(InterfaceConstants.UNIT_JSON_KEY) != null) {
					UnitHandle unitHandler = new UnitHandle();
					JsonObject unitJSONObject = additionInfoJSONObject.get(InterfaceConstants.UNIT_JSON_KEY).getAsJsonObject();
					unitHandler.setKey(unitJSONObject.get(InterfaceConstants.JSON_KEY).getAsString());
					unitHandler.setAgency(unitJSONObject.get(InterfaceConstants.AGENCY_JSON_KEY).getAsString());
					unitHandler.setCallSign(unitJSONObject.get(InterfaceConstants.CALL_SIGN_JSON_KEY).getAsString());
					unitHandler.setShiftId(unitJSONObject.get(InterfaceConstants.SHIFT_ID_JSON_KEY).getAsString());
					additionalInfo.setUnit(unitHandler);
				}
				// gets the Jurisdictions from addition object, translate to UserSession->AdditionalInfo-> List<Jurisdiction>
				if (additionInfoJSONObject.get(InterfaceConstants.JURISDICTIONS_JSON_KEY) != null) {
					//gets the array of Jurisdictions json array
					JsonArray jurisdictionsJSONArray = additionInfoJSONObject.get(InterfaceConstants.JURISDICTIONS_JSON_KEY).getAsJsonArray();
					List<Jurisdiction> jurisdictions = new ArrayList<>();
					for (JsonElement element : jurisdictionsJSONArray) {
						JsonObject elementJSON = element.getAsJsonObject();
						Jurisdiction jurisdiction = new Jurisdiction();
						//area
						if (elementJSON.get(InterfaceConstants.AREA_JSON_KEY) != null) {
							JsonObject areaJSON = elementJSON.get(InterfaceConstants.AREA_JSON_KEY).getAsJsonObject();
							Lookup areaLookup = new Lookup();
							areaLookup.setUid(areaJSON.get(InterfaceConstants.UID_JSON_KEY).getAsString());
							jurisdiction.setArea(areaLookup);
						}
						//sector
						if (elementJSON.get(InterfaceConstants.SECTOR_JSON_KEY) != null) {
							JsonObject sectorJSON = elementJSON.get(InterfaceConstants.SECTOR_JSON_KEY).getAsJsonObject();
							Lookup sectorLookup = new Lookup();
							sectorLookup.setUid(sectorJSON.get(InterfaceConstants.UID_JSON_KEY).getAsString());
							jurisdiction.setSector(sectorLookup);
						}
						//zone
						if (elementJSON.get(InterfaceConstants.ZONE_JSON_KEY) != null) {
							JsonObject zoneJSON = elementJSON.get(InterfaceConstants.ZONE_JSON_KEY).getAsJsonObject();
							Lookup zoneLookup = new Lookup();
							zoneLookup.setUid(zoneJSON.get(InterfaceConstants.UID_JSON_KEY).getAsString());
							jurisdiction.setZone(zoneLookup);
						}
						jurisdictions.add(jurisdiction);
					}
					additionalInfo.setJurisdictions(jurisdictions);
				}
				// gets the District from addition object, translate to UserSession->AdditionalInfo->district field
				if (additionInfoJSONObject.get(InterfaceConstants.DISTRICT_JSON_KEY) != null) {
					Lookup districtLookup = new Lookup();
					JsonObject districtJSON = additionInfoJSONObject.get(InterfaceConstants.DISTRICT_JSON_KEY).getAsJsonObject();
					districtLookup.setUid(districtJSON.get(InterfaceConstants.UID_JSON_KEY).getAsString());
					additionalInfo.setDistrict(districtLookup);
				}

				// gets the Station from addition object, translate to UserSession->AdditionalInfo->station field
				if (additionInfoJSONObject.get(InterfaceConstants.STATION_JSON_KEY) != null) {
					Lookup stationLookup = new Lookup();
					JsonObject stationJSON = additionInfoJSONObject.get(InterfaceConstants.STATION_JSON_KEY).getAsJsonObject();
					stationLookup.setUid(stationJSON.get(InterfaceConstants.UID_JSON_KEY).getAsString());
					additionalInfo.setStation(stationLookup);
				}

				// gets the VehicleId from addition object, translate to UserSession->AdditionalInfo->vehicle field
				if (additionInfoJSONObject.get(InterfaceConstants.VEHICLE_ID_JSON_KEY) != null) {
					additionalInfo.setVehicleId(additionInfoJSONObject.get(InterfaceConstants.VEHICLE_ID_JSON_KEY).getAsString());
				}

				// gets trustedAgencies from addition object, translate to UserSession->AdditionalInfo->trustedAgencies field
				if (additionInfoJSONObject.get(InterfaceConstants.TRUSTED_AGENCIES_JSON_KEY) != null) {
					JsonArray trustedAgienciesJson = additionInfoJSONObject.get(InterfaceConstants.TRUSTED_AGENCIES_JSON_KEY).getAsJsonArray();
					List<Lookup> trustedAgencies = new ArrayList<>();
					for (JsonElement element : trustedAgienciesJson) {
						JsonObject elementJSON = element.getAsJsonObject();
						if (elementJSON.get(InterfaceConstants.UID_JSON_KEY) != null){
							Lookup trustedAgency = new Lookup();
							trustedAgency.setUid(elementJSON.get(InterfaceConstants.UID_JSON_KEY).getAsString());
							trustedAgencies.add(trustedAgency);
						}
					}
					additionalInfo.setTrustedAgencies(trustedAgencies);
				}
				userSession.setAdditionalInfo(additionalInfo);
			}
		}
		result.setModel(userSession);

		return result;
	}

	@Override public BookOffParameters translateBookOffParameters(JsonObject payload) {
		//TODO translation logic must be here
		return new BookOffParameters();
	}
}
