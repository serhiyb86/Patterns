/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.translation;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.models.representation.AdditionalInfo;
import com.models.representation.BookOffParameters;
import com.models.representation.BookOnParameters;
import com.models.representation.Lookup;

import java.util.ArrayList;
import java.util.List;

import static com.constants.InterfaceConstants.ADDITIONAL_INFO;
import static com.constants.InterfaceConstants.DEVICE_ID;
import static com.constants.InterfaceConstants.IS_REMOVE_PREV_SESSION;
import static com.constants.InterfaceConstants.IS_USE_UNIT_PREASSIGNMENTS;
import static com.constants.InterfaceConstants.REQUEST_PARAMETERS;
import static com.constants.InterfaceConstants.ROLE_KEY;
import static com.constants.InterfaceConstants.STATION;
import static com.constants.InterfaceConstants.USER_ID;

/**
 * Translator from the on-premise format to the cloud api models
 */
public class CadCloudTranslator {

	public static BookOnParameters translateBookOn(JsonObject payload) {
		if(payload.get(REQUEST_PARAMETERS) != null) {
			//TODO translation logic must be here, this is preliminary code
			BookOnParameters bookOn = new BookOnParameters();
			JsonObject requestParameters = payload.get(REQUEST_PARAMETERS).getAsJsonObject();
			bookOn.setDeviceId(requestParameters.get(DEVICE_ID).getAsString());
			bookOn.setIsRemovePrevSession(requestParameters.get(IS_REMOVE_PREV_SESSION).getAsBoolean());
			bookOn.setIsUseUnitPreassignments(requestParameters.get(IS_USE_UNIT_PREASSIGNMENTS).getAsBoolean());
			bookOn.setRoleKey(requestParameters.get(ROLE_KEY).getAsString());
			bookOn.setUserId(requestParameters.get(USER_ID).getAsString());

			if(requestParameters.get(ADDITIONAL_INFO) != null) {
				JsonArray additionalInfo = requestParameters.get(ADDITIONAL_INFO).getAsJsonArray();
				List<AdditionalInfo> additionalInfoList = new ArrayList<>();
				for (JsonElement element : additionalInfo) {
					AdditionalInfo info = new AdditionalInfo();
					Lookup station = new Lookup();
					station.setUid(element.getAsJsonObject().get(STATION).getAsString());
					info.setStation(station);
					//info.setUnit();
					//info.setVehicleId();
					//info.setDistrict();
					//info.setTrustedAgencies();
					additionalInfoList.add(info);
				}
				bookOn.setAdditionalInfo(additionalInfoList);
			}

			return bookOn;
		}
		return null;

	}
	public static BookOffParameters translateBookOff(JsonObject payload) {
		//TODO translation logic must be here
		return new BookOffParameters();
	}
}
