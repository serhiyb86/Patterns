/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.RoleHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link RoleHandle} object.
 */
public class RoleHandleMapper {

	private static final Map<String, Setter<RoleHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.ROLE_NAME, new StringSetter<>(RoleHandle::setNameCode));
		setters.put(InterfaceConstants.BookOnProperties.ROLE_AGENCY_ALIAS, new StringSetter<>(RoleHandle::setAgencyAlias));
	}

	/**
	 * Creates {@link RoleHandle} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link RoleHandle} object with mapped data.
	 */
	public RoleHandle createAndMapRoleHandle(JsonObject data) {
		RoleHandle roleHandle = new RoleHandle();
		if (data != null) {
			mapToRoleHandle(data, roleHandle);
		}
		return roleHandle;
	}

	/**
	 * Sets mapped data from json to the {@link RoleHandle} object.
	 *
	 * @param data json data.
	 * @param roleHandle target object.
	 */
	private void mapToRoleHandle(JsonObject data, RoleHandle roleHandle) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(roleHandle, data.get(key));
			}
		});
	}

}
