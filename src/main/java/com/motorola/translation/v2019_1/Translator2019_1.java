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
import com.motorola.models.representation.BookOnParameters;
import com.motorola.models.representation.Lookup;
import com.motorola.translation.BaseTranslator;

import java.util.ArrayList;
import java.util.List;

/**
 * Translator class for Spillman version 2019.1
 */
public class Translator2019_1 implements BaseTranslator {

	@Override
	public BookOnParameters translateBookOnParameters(JsonObject payload) {
		if (payload.get(InterfaceConstants.REQUEST_PARAMETERS) != null) {
			//TODO translation logic must be here, this is preliminary code
			BookOnParameters bookOn = new BookOnParameters();
			JsonObject requestParameters = payload.get(InterfaceConstants.REQUEST_PARAMETERS).getAsJsonObject();
			bookOn.setDeviceId(requestParameters.get(InterfaceConstants.DEVICE_ID).getAsString());
			bookOn.setIsRemovePrevSession(requestParameters.get(InterfaceConstants.IS_REMOVE_PREV_SESSION).getAsBoolean());
			bookOn.setIsUseUnitPreassignments(requestParameters.get(InterfaceConstants.IS_USE_UNIT_PREASSIGNMENTS).getAsBoolean());
			bookOn.setRoleKey(requestParameters.get(InterfaceConstants.ROLE_KEY).getAsString());
			bookOn.setUserId(requestParameters.get(InterfaceConstants.USER_ID).getAsString());

			if (requestParameters.get(InterfaceConstants.ADDITIONAL_INFO) != null) {
				JsonArray additionalInfo = requestParameters.get(InterfaceConstants.ADDITIONAL_INFO).getAsJsonArray();
				List<AdditionalInfo> additionalInfoList = new ArrayList<>();
				for (JsonElement element : additionalInfo) {
					AdditionalInfo info = new AdditionalInfo();
					Lookup station = new Lookup();
					station.setUid(element.getAsJsonObject().get(InterfaceConstants.STATION).getAsString());
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

	@Override public BookOffParameters translateBookOffParameters(JsonObject payload) {
		//TODO translation logic must be here
		return new BookOffParameters();
	}
}
