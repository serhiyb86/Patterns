/*
 * Copyright 2020 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Nature;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Nature data to the {@link Nature} object.
 */
public class NatureMapper {

	private static final Map<String, Setter<Nature>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISCIPLINE_NATURE, new StringSetter<>(Nature::setNatureKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DESCRIPTION, new StringSetter<>(Nature::setDescription));
	}

	/**
	 * Creates {@link Nature} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link Nature} object with mapped data.
	 */
	public Nature createAndMapNature(JsonObject data) {
		Nature nature = new Nature();
		if (data != null) {
			mapToNature(data, nature);
		}
		return nature;
	}

	/**
	 * Sets mapped data from json to the {@link Nature} object.
	 *
	 * @param data json data.
	 * @param nature target object.
	 */
	private void mapToNature(JsonObject data, Nature nature) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(nature, data.get(key));
			}
		});
	}
}
