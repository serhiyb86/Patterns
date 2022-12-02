/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.IncidentFeed;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with IncidentHandle data to the {@link IncidentFeed} object.
 */
public class IncidentFeedMapper extends GenericMapper<IncidentFeed> {

	private static final Map<String, Setter<IncidentFeed>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.AssignedIncident.CALL_ID, (model, value) -> {
			String callId = ((JsonElement) value).getAsString();
			model.setKey(callId);
			model.setAlias(callId);
		});
		setters.put(InterfaceConstants.Unit.AssignedIncident.KEY, new StringSetter<>(IncidentFeed::setKey));
		setters.put(InterfaceConstants.Unit.AssignedIncident.ALIAS, new StringSetter<>(IncidentFeed::setAlias));
		setters.put(InterfaceConstants.Unit.AssignedIncident.DISPATCH_KEY, new StringSetter<>(IncidentFeed::setDispatchKey));
		setters.put(InterfaceConstants.Unit.AssignedIncident.DISPATCH_ALIAS, new StringSetter<>(IncidentFeed::setDispatchAlias));
	}

	public IncidentFeedMapper() {
		super(setters);
	}

	public IncidentFeed createAndMapToIncidentHandle(JsonObject incidentHandleData) {
		IncidentFeed incidentFeed = new IncidentFeed();
		setters.forEach((key, value) -> {
			if (incidentHandleData.get(key) != null) {
				value.accept(incidentFeed, incidentHandleData.get(key));
			}
		});
		// TODO: remove after general validation logic will be added.
		if (StringUtils.isBlank(incidentFeed.getKey()) || StringUtils.isBlank(incidentFeed.getDispatchKey())) {
			return null;
		}
		return incidentFeed;
	}
}
