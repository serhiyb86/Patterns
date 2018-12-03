/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.bookon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.UserSession;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for converting Json Object with appropriate data to the {@link UserSession} object.
 */
public class UserSessionMapper {

	private static final Map<String, Setter<UserSession>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.CUSTOMER_ID, new StringSetter<>(UserSession::setCustomerId));
		setters.put(InterfaceConstants.BookOnProperties.SESSION_ID, new StringSetter<>(UserSession::setSessionId));
		setters.put(InterfaceConstants.BookOnProperties.DEVICE_ID, new StringSetter<>(UserSession::setDeviceId));
		setters.put(InterfaceConstants.BookOnProperties.USER_ID, new StringSetter<>(UserSession::setUserId));
		setters.put(InterfaceConstants.BookOnProperties.WHEN_SESSION_CREATED,
			new StringSetter<>(UserSession::setWhenSessionCreated));
		setters.put(InterfaceConstants.BookOnProperties.WHEN_SESSION_UPDATED,
			new StringSetter<>(UserSession::setWhenSessionUpdated));
		//	will be changed after clarifications of the onPrem permission model.
		setters.put(InterfaceConstants.BookOnProperties.API_ACCESS_LIST, (model, value) -> {
			List<String> result = new ArrayList<>();
			if (value != null) {
				for (JsonElement element : (JsonArray) value) {
					JsonObject permissionJSON = element.getAsJsonObject();
					result.add(CadCloudUtils.getStringByKey(permissionJSON, InterfaceConstants.BookOnProperties.PERMISSION_ID));
				}
			}
			model.setApiAccessList(result);
		});
		setters.put(InterfaceConstants.BookOnProperties.MONITOR_AREAS, (model, value) ->
			model.setMonitorAreas(new MonitorAreasMapper().createAndMapToMonitorAreas((JsonObject) value))
		);
		setters.put(InterfaceConstants.BookOnProperties.ADDITIONAL_INFO_JSON_KEY, (model, value) ->
			model.setAdditionalInfo(new AdditionalInfoMapper().createAndMapToAdditionalInfo((JsonObject) value))
		);
	}

	/**
	 * Creates {@link UserSession} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link UserSession} object with mapped data.
	 */
	public UserSession createAndMapToUserSession(Set<Map.Entry<String, JsonElement>> data) {
		UserSession userSession = new UserSession();
		return mapToUserSession(data, userSession);
	}

	/**
	 * Sets mapped data from json to the {@link UserSession} object.
	 *
	 * @param data json data.
	 * @param userSession target object.
	 * @return filled target object with mapped data.
	 */
	private UserSession mapToUserSession(Set<Map.Entry<String, JsonElement>> data, UserSession userSession) {
		data.forEach(entry -> {
			Setter<UserSession> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(userSession, entry.getValue());
			}
		});
		return userSession;
	}

}
