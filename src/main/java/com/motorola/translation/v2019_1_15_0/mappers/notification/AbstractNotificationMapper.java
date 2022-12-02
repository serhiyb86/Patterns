package com.motorola.translation.v2019_1_15_0.mappers.notification;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.Config;
import com.motorola.models.representation.ApiError;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.setter.ZonedDateTimeSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract mapper with common setters for objects that extends {@link  ResponseNotification} class.
 */
public abstract class AbstractNotificationMapper<T extends ResponseNotification> {

	private final Map<String, Setter<T>> setters = new HashMap<>();

	protected AbstractNotificationMapper(Map<String, Setter<T>> additionalSetters) {
		setters.put(InterfaceConstants.NotificationProperties.CORRELATION_ID, new StringSetter<>(T::setCorrelationId));
		setters.put(InterfaceConstants.NotificationProperties.SERVICE_ID, new StringSetter<>(T::setServiceId));
		setters.put(InterfaceConstants.NotificationProperties.CUSTOMER_ID, new StringSetter<>(T::setCustomerId));
		setters.put(InterfaceConstants.NotificationProperties.NOTIFICATION_TYPE, new StringSetter<>(T::setNotificationType));
		setters.put(InterfaceConstants.NotificationProperties.WHEN_SUBMITTED, new ZonedDateTimeSetter<>(T::setWhenSubmitted, Config.DATETIME_FORMAT));
		setters.put(InterfaceConstants.NotificationProperties.SESSION_ID, new StringSetter<>(T::setSessionId));
		setters.put(InterfaceConstants.NotificationProperties.RESULT_NATURE, new StringSetter<>(T::setResultNature));
		setters.put(InterfaceConstants.NotificationProperties.RESPONSE_TYPE, new StringSetter<>(T::setResponseType));
		setters.put(InterfaceConstants.NotificationProperties.RESPONSE_DATA, new StringSetter<>(T::setResponseData));
		setters.put(InterfaceConstants.NotificationProperties.ERROR, (model, value) -> {
			Map<String, Setter<ApiError>> errorSetters = new HashMap<>();
			errorSetters.put(InterfaceConstants.NotificationProperties.ERROR_CODE, new StringSetter<>(ApiError::setErrorCode));
			errorSetters.put(InterfaceConstants.NotificationProperties.MESSAGE, new StringSetter<>(ApiError::setMessage));
			model.setError(new GenericMapper<>(errorSetters).mapToModel((JsonObject) value, new ApiError()));
		});
		setters.put(InterfaceConstants.NotificationProperties.KEY_TYPE, new StringSetter<>(T::setKeyType));
		setters.put(InterfaceConstants.NotificationProperties.KEY, new StringSetter<>(T::setKey));
		setters.putAll(additionalSetters);
	}

	public T mapToModel(JsonObject data, T model) {
		if (data != null) {
			data.entrySet().forEach(entry -> {
				Setter<T> consumer = this.setters.get(entry.getKey());
				if (consumer != null) {
					consumer.accept(model, entry.getValue());
				}
			});
		}
		return model;
	}
}
