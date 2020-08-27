/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.UnitFeed;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link UnitFeed} object.
 */
public class UnitHandleMapper {

	private static final Map<String, Setter<UnitFeed>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.UNIT_KEY, new StringSetter<>(UnitFeed::setKey));
		setters.put(InterfaceConstants.BookOnProperties.UNIT_AGENCY_ALIAS, new StringSetter<>(UnitFeed::setAgencyAlias));
		setters.put(InterfaceConstants.BookOnProperties.UNIT_CALLSIGN, new StringSetter<>(UnitFeed::setCallSign));
	}

	/**
	 * Creates {@link UnitFeed} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link UnitFeed} object with mapped data.
	 */
	public UnitFeed createAndMapUnitHandle(JsonObject data) {
		UnitFeed unitFeed = new UnitFeed();
		if (data != null) {
			mapToUnitHandle(data, unitFeed);
		}
		return unitFeed;
	}

	/**
	 * Sets mapped data from json to the {@link UnitFeed} object.
	 *
	 * @param data json data.
	 * @param unitFeed target object.
	 */
	private void mapToUnitHandle(JsonObject data, UnitFeed unitFeed) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(unitFeed, data.get(key));
			}
		});
	}
}
