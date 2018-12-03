/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.unit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.IncidentHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with IncidentHandle data to the {@link IncidentHandle} object.
 */
public class IncidentHandleMapper {

	private static final Map<String, Setter<IncidentHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.AssignedIncident.KEY, (model, value) -> {
			String id = ((JsonElement) value).getAsString();
			model.setKey(id);
			model.setId(id);
		});
		setters.put(InterfaceConstants.Unit.AssignedIncident.DISPATCH_KEY, new StringSetter<>(IncidentHandle::setDispatchKey));
		setters.put(InterfaceConstants.Unit.AssignedIncident.DISPATCH_ALIAS, new StringSetter<>(IncidentHandle::setDispatchAlias));
	}

	public IncidentHandle createAndMapToIncidentHandle(JsonObject incidentHandleData) {
		IncidentHandle incidentHandle = new IncidentHandle();
		incidentHandleData.entrySet().forEach(entry -> {
			Setter<IncidentHandle> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(incidentHandle, entry.getValue());
			}
		});
		return incidentHandle;
	}
}
