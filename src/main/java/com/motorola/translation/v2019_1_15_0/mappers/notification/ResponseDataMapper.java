/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.notification;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Alert;
import com.motorola.models.representation.ResponseData;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.v2019_1_15_0.mappers.incident.AlertMapper;
import com.motorola.utils.CadCloudUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link  ResponseData} object.
 */
public class ResponseDataMapper extends AbstractNotificationMapper<ResponseData> {

	private static final Map<String, Setter<ResponseData>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.NotificationProperties.PAYLOAD, ((model, value) -> {
			JsonObject jsonObject = JsonParser.parseString(((JsonPrimitive) value).getAsString()).getAsJsonObject();
			JsonArray data = CadCloudUtils.getJsonArrayByKey(jsonObject, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
			if (data != null && data.get(0) != null) {
				JsonObject addressAlerts = data.get(0).getAsJsonObject();
				AlertMapper alertMapper = new AlertMapper();
				List<Alert> alerts = alertMapper.createAndMapToAlertList(addressAlerts);
				String alertsJsonString = CadCloudUtils.convertObjectToJsonString(alerts);
				model.setPayload(alertsJsonString);
			}
		}));
	}

	public ResponseDataMapper() {
		super(setters);
	}

	/**
	 * Creates {@link ResponseData} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link ResponseData} object with mapped data.
	 */
	public ResponseData createAndMapToResponseNotification(JsonObject data) {
		ResponseData responseData = new ResponseData();
		return mapToModel(data, responseData);
	}

}
