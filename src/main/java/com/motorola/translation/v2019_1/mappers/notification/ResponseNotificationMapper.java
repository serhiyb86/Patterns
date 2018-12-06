/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.notification;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.ApiError;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		setters.put(InterfaceConstants.NotificationProperties.WHEN_SUBMITTED, new StringSetter<>(ResponseNotification::setWhenSubmitted));
		setters.put(InterfaceConstants.NotificationProperties.SESSION_ID, new StringSetter<>(ResponseNotification::setSessionId));
		setters.put(InterfaceConstants.NotificationProperties.RESULT_NATURE, new StringSetter<>(ResponseNotification::setResultNature));
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
