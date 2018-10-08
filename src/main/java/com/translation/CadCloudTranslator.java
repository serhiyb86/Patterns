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

import static com.constants.InterfaceConstants.REQUEST_PARAMETERS;

/**
 * Translator from the on-premise format to the cloud api models
 */
public class CadCloudTranslator {

	public static BookOnParameters translateBookOn(JsonObject payload) {
		if(payload.get(REQUEST_PARAMETERS) != null) {
			//TODO translation logic must be here, this is preliminary code
			BookOnParameters bookOn = new BookOnParameters();
			JsonObject requestParameters = payload.get(REQUEST_PARAMETERS).getAsJsonObject();
			bookOn.setDeviceId(requestParameters.get("deviceId").getAsString());
			bookOn.setIsRemovePrevSession(requestParameters.get("isRemovePrevSession").getAsBoolean());
			bookOn.setIsUseUnitPreassignments(requestParameters.get("isUseUnitPreassignments").getAsBoolean());
			bookOn.setRoleKey(requestParameters.get("roleKey").getAsString());
			bookOn.setUserId(requestParameters.get("userId").getAsString());

			if(requestParameters.get("additionalInfo") != null) {
				JsonArray additionalInfo = requestParameters.get("additionalInfo").getAsJsonArray();
				List<AdditionalInfo> additionalInfoList = new ArrayList<>();
				for (JsonElement element : additionalInfo) {
					AdditionalInfo info = new AdditionalInfo();
					Lookup station = new Lookup();
					station.setUid(element.getAsJsonObject().get("station").getAsString());
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
