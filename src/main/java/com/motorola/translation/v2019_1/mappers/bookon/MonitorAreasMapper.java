/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.bookon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.MonitorAreas;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1.mappers.GenericMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link MonitorAreas} object.
 */
public class MonitorAreasMapper {

	private static final Map<String, Setter<MonitorAreas>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.AREAS, (model, value) -> {
			if (value != null) {
				HashMap<String, Setter<Lookup>> innerSetters = new HashMap<>();
				innerSetters.put(InterfaceConstants.GeneralProperties.UID_JSON_KEY, new StringSetter<>(Lookup::setUid));
				GenericMapper<Lookup> mapper = new GenericMapper<>(innerSetters);
				List<Lookup> monitorAreas = new ArrayList<>();
				for (JsonElement element : ((JsonArray) value)) {
					if (element != null) {
						monitorAreas.add(mapper.mapToModel(element.getAsJsonObject(), new Lookup()));
					}
				}
				model.setAreaKeys(monitorAreas);
			}
		});
	}

	/**
	 * Creates {@link MonitorAreas} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link MonitorAreas} object with mapped data.
	 */
	public MonitorAreas createAndMapToMonitorAreas(JsonObject data) {
		MonitorAreas monitorAreas = new MonitorAreas();
		if (data != null) {
			mapToMonitorAreas(data, monitorAreas);
		}
		return monitorAreas;
	}

	/**
	 * Sets mapped data from json to the {@link MonitorAreas} object.
	 *
	 * @param data json data.
	 * @param monitorAreas target object.
	 * @return filled target object with mapped data.
	 */
	public MonitorAreas mapToMonitorAreas(JsonObject data, MonitorAreas monitorAreas) {
		data.entrySet().forEach(entry -> {
			Setter<MonitorAreas> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(monitorAreas, entry.getValue());
			}
		});
		return monitorAreas;
	}

}
