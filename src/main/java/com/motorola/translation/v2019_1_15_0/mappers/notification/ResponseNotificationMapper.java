/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.notification;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.Config;
import com.motorola.models.representation.Alert;
import com.motorola.models.representation.ApiError;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.setter.ZonedDateTimeSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;
import com.motorola.translation.v2019_1_15_0.mappers.incident.AlertMapper;
import com.motorola.utils.CadCloudUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link  ResponseNotification} object.
 */
public class ResponseNotificationMapper {

	private static final Map<String, Setter<ResponseNotification>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.NotificationProperties.CORRELATION_ID, new StringSetter<>(ResponseNotification::setCorrelationId));
		setters.put(InterfaceConstants.NotificationProperties.SERVICE_ID, new StringSetter<>(ResponseNotification::setServiceId));
		setters.put(InterfaceConstants.NotificationProperties.CUSTOMER_ID, new StringSetter<>(ResponseNotification::setCustomerId));
		setters.put(InterfaceConstants.NotificationProperties.NOTIFICATION_TYPE, new StringSetter<>(ResponseNotification::setNotificationType));
		setters.put(InterfaceConstants.NotificationProperties.WHEN_SUBMITTED, new ZonedDateTimeSetter<>(ResponseNotification::setWhenSubmitted, Config.DATETIME_FORMAT));
		setters.put(InterfaceConstants.NotificationProperties.SESSION_ID, new StringSetter<>(ResponseNotification::setSessionId));
		setters.put(InterfaceConstants.NotificationProperties.RESULT_NATURE, new StringSetter<>(ResponseNotification::setResultNature));
		setters.put(InterfaceConstants.NotificationProperties.RESPONSE_DATA, ((model, value) -> {
			JsonParser jsonParser = new JsonParser();
			JsonObject jsonObject = jsonParser.parse(((JsonPrimitive) value).getAsString()).getAsJsonObject();
			JsonArray data = CadCloudUtils.getJsonArrayByKey(jsonObject, InterfaceConstants.GeneralProperties.DATA_JSON_KEY);
			JsonObject addressAlerts = data.get(0).getAsJsonObject();
			AlertMapper alertMapper = new AlertMapper();
			List<Alert> alerts = alertMapper.createAndMapToAlertList(addressAlerts);
			model.setResponseData(alerts);
		}));
		setters.put(InterfaceConstants.NotificationProperties.RESPONSE_TYPE, new StringSetter<>(ResponseNotification::setResponseType));
		setters.put(InterfaceConstants.NotificationProperties.ERROR, (model, value) -> {
			Map<String, Setter<ApiError>> setters = new HashMap<>();
			setters.put(InterfaceConstants.NotificationProperties.ERROR_CODE, new StringSetter<>(ApiError::setErrorCode));
			setters.put(InterfaceConstants.NotificationProperties.MESSAGE, new StringSetter<>(ApiError::setMessage));
			model.setError(new GenericMapper<>(setters).mapToModel((JsonObject) value, new ApiError()));
		});

	}

	/**
	 * Creates {@link ResponseNotification} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link ResponseNotification} object with mapped data.
	 */
	public ResponseNotification createAndMapToResponseNotification(JsonObject data) {
		ResponseNotification notification = new ResponseNotification();
		return mapToResponseNotification(data, notification);
	}

	/**
	 * Sets mapped data from json to the {@link ResponseNotification} object.
	 *
	 * @param data json data.
	 * @param notification target object.
	 * @return filled target object with mapped data.
	 */
	public ResponseNotification mapToResponseNotification(JsonObject data, ResponseNotification notification) {
		data.entrySet().forEach(entry -> {
			Setter<ResponseNotification> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(notification, entry.getValue());
			}
		});
		return notification;
	}

}
