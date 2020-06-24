/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.IncidentHandle;
import com.motorola.models.representation.Personnel;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with IncidentHandle data to the {@link IncidentHandle} object.
 */
public class IncidentHandleMapper extends GenericMapper<IncidentHandle> {

	private static final Map<String, Setter<IncidentHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.AssignedIncident.CALL_ID, (model, value) -> {
			String callId = ((JsonElement) value).getAsString();
			model.setKey(callId);
			model.setAlias(callId);
		});
		setters.put(InterfaceConstants.Unit.AssignedIncident.KEY, new StringSetter<>(IncidentHandle::setKey));
		setters.put(InterfaceConstants.Unit.AssignedIncident.ALIAS, new StringSetter<>(IncidentHandle::setAlias));
		setters.put(InterfaceConstants.Unit.AssignedIncident.DISPATCH_KEY, new StringSetter<>(IncidentHandle::setDispatchKey));
		setters.put(InterfaceConstants.Unit.AssignedIncident.DISPATCH_ALIAS, new StringSetter<>(IncidentHandle::setDispatchAlias));
	}

	public IncidentHandleMapper() {
		super(setters);
	}

	public IncidentHandle createAndMapToIncidentHandle(JsonObject incidentHandleData) {
		IncidentHandle incidentHandle = new IncidentHandle();
		setters.forEach((key, value) -> {
			if (incidentHandleData.get(key) != null) {
				value.accept(incidentHandle, incidentHandleData.get(key));
			}
		});
		// TODO: remove after general validation logic will be added.
		if (StringUtils.isBlank(incidentHandle.getKey()) || StringUtils.isBlank(incidentHandle.getDispatchKey())) {
			return null;
		}
		return incidentHandle;
	}
}
