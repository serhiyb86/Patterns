/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.Config;
import com.motorola.models.representation.AccessScope;
import com.motorola.models.representation.MonitorAreas;
import com.motorola.models.representation.RoleHandle;
import com.motorola.models.representation.UserSession;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.setter.ZonedDateTimeSetter;
import com.motorola.utils.CadCloudUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link UserSession} object.
 */
public class UserSessionMapper {

	private static final Map<String, Setter<UserSession>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.CUSTOMER_ID, new StringSetter<>(UserSession::setCustomerId));
		setters.put(InterfaceConstants.BookOnProperties.SESSION_ID, new StringSetter<>(UserSession::setSessionId));
		setters.put(InterfaceConstants.BookOnProperties.SERVICE_ID, new StringSetter<>(UserSession::setServiceId));
		setters.put(InterfaceConstants.BookOnProperties.DEVICE, (model, value) ->
			model.setDevice(new DeviceHandleMapper().createAndMapDeviceHandle((JsonObject) value))
		);
		setters.put(InterfaceConstants.BookOnProperties.CAD_USER_AGENCY_KEY, new StringSetter<>(UserSession::setCadUserAgencyKey));
		setters.put(InterfaceConstants.BookOnProperties.DEVICE_AGENCY_KEY, new StringSetter<>(UserSession::setDeviceAgencyKey));
		setters.put(InterfaceConstants.BookOnProperties.CAD_USER, (model, value) ->
			model.setCadUser(new PersonnelHandleMapper().createAndMapPersonnelHandle((JsonObject) value))
		);
		setters.put(InterfaceConstants.BookOnProperties.UNIT_JSON_KEY, (model, value) ->
			model.setUnit(new UnitHandleMapper().createAndMapUnitHandle((JsonObject) value))
		);
		setters.put(InterfaceConstants.BookOnProperties.ROLE, (model, value) -> {
			RoleHandle role = new RoleHandle();
			role.setKey(InterfaceConstants.BookOnProperties.ROLE_KEY_VAL);
			model.setRole(role);
		});
		setters.put(InterfaceConstants.BookOnProperties.WHEN_SESSION_CREATED,
			new ZonedDateTimeSetter<>(UserSession::setWhenSessionCreated, Config.DATETIME_FORMAT));
		setters.put(InterfaceConstants.BookOnProperties.WHEN_SESSION_UPDATED,
			new ZonedDateTimeSetter<>(UserSession::setWhenSessionUpdated, Config.DATETIME_FORMAT));
		//	will be changed after clarifications of the onPrem permission model.
		setters.put(InterfaceConstants.BookOnProperties.API_ACCESS_SCOPE, (model, value) -> {
			if (value != null) {
				List<AccessScope> result = new ArrayList<>();
				for (JsonElement element : (JsonArray) value) {
					JsonObject permissionJSON = element.getAsJsonObject();
					AccessScope accessScope = new AccessScope();
					accessScope.setAgencyKey(CadCloudUtils.getStringByKey(permissionJSON, InterfaceConstants.BookOnProperties.AGENCY_KEY));
					List<String> permissions = new ArrayList<>();
					for (JsonElement permission : permissionJSON.getAsJsonArray(InterfaceConstants.BookOnProperties.API_ACCESS_LIST)) {
						permissions.add(permission.getAsString());
					}
					accessScope.setApiAccessList(permissions);
					result.add(accessScope);
				}
				model.setApiAccessScope(result);
			}
		});
		setters.put(InterfaceConstants.BookOnProperties.MONITOR_AREAS, (model, value) -> {
				if (value != null) {
					MonitorAreas monitorAreas = new MonitorAreas();
					monitorAreas.setAreaKeys(Collections.singletonList(((JsonElement) value).getAsString()));
					model.setMonitorAreas(monitorAreas);
				}
			}
		);

	}

	/**
	 * Creates {@link UserSession} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link UserSession} object with mapped data.
	 */
	public UserSession createAndMapToUserSession(JsonObject data) {
		UserSession userSession = new UserSession();
		RoleHandle role = new RoleHandle();
		role.setKey(InterfaceConstants.BookOnProperties.ROLE_KEY_VAL);
		userSession.setRole(role);
		return mapToUserSession(data, userSession);
	}

	/**
	 * Sets mapped data from json to the {@link UserSession} object.
	 *
	 * @param data json data.
	 * @param userSession target object.
	 * @return filled target object with mapped data.
	 */
	private UserSession mapToUserSession(JsonObject data, UserSession userSession) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(userSession, data.get(key));
			}
		});
		return userSession;
	}

}
