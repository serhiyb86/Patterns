/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.UnitHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link UnitHandle} object.
 */
public class UnitHandleMapper {

	private static final Map<String, Setter<UnitHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.UNIT_KEY, new StringSetter<>(UnitHandle::setKey));
		setters.put(InterfaceConstants.BookOnProperties.UNIT_AGENCY_ALIAS, new StringSetter<>(UnitHandle::setAgencyAlias));
		setters.put(InterfaceConstants.BookOnProperties.UNIT_CALLSIGN, new StringSetter<>(UnitHandle::setCallSign));
	}

	/**
	 * Creates {@link UnitHandle} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link UnitHandle} object with mapped data.
	 */
	public UnitHandle createAndMapUnitHandle(JsonObject data) {
		UnitHandle unitHandle = new UnitHandle();
		if (data != null) {
			mapToUnitHandle(data, unitHandle);
		}
		return unitHandle;
	}

	/**
	 * Sets mapped data from json to the {@link UnitHandle} object.
	 *
	 * @param data json data.
	 * @param unitHandle target object.
	 */
	private void mapToUnitHandle(JsonObject data, UnitHandle unitHandle) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(unitHandle, data.get(key));
			}
		});
	}
}
