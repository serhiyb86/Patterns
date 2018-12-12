/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.AdditionalInfo;
import com.motorola.models.representation.UnitHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link AdditionalInfo} object.
 */
public class AdditionalInfoMapper {

	private static final Map<String, Setter<AdditionalInfo>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.UNIT_JSON_KEY, (model, value) -> {
			Map<String, Setter<UnitHandle>> additionalInfoSetters = new HashMap<>();
			additionalInfoSetters.put(InterfaceConstants.BookOnProperties.KEY_JSON_KEY, new StringSetter<>(UnitHandle::setKey));
			additionalInfoSetters.put(InterfaceConstants.BookOnProperties.AGENCY_JSON_KEY, new StringSetter<>(UnitHandle::setAgency));
			model.setUnit(new GenericMapper<>(additionalInfoSetters).mapToModel((JsonObject) value, new UnitHandle()));
		});
	}

	/**
	 * Creates {@link AdditionalInfo} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 */
	public AdditionalInfo createAndMapToAdditionalInfo(JsonObject data) {
		AdditionalInfo additionalInfo = new AdditionalInfo();
		if (data != null) {
			mapToAdditionalInfo(data, additionalInfo);
		}
		return additionalInfo;
	}

	/**
	 * Sets mapped data from json to the {@link AdditionalInfo} object.
	 *
	 * @param data json data.
	 * @param additionalInfo target object.
	 * @return filled target object with mapped data.
	 */
	public AdditionalInfo mapToAdditionalInfo(JsonObject data, AdditionalInfo additionalInfo) {
		data.entrySet().forEach(entry -> {
			Setter<AdditionalInfo> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(additionalInfo, entry.getValue());
			}
		});
		return additionalInfo;
	}

}
