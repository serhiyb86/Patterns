/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.unit;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.JurisdictionalAssignment;
import com.motorola.models.representation.Lookup;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.v2019_1.mappers.AbstractMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with JurisdictionalAssignment data to the {@link JurisdictionalAssignment} object.
 */
public class JurisdictionalAssignmentMapper extends AbstractMapper {

	private static final Map<String, Setter<JurisdictionalAssignment>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.JurisdictionalAssignment.BEATS, ((model, value) -> {
			List<Lookup> result = new ArrayList<>();
			if (value != null) {
				for (JsonElement element : (JsonArray) value) {
					result.add(createLookup(element));
				}
			}
			model.setBeats(result);
		}));
	}

	public JurisdictionalAssignment createAndMapToJurisdictionalAssignment(JsonObject assignmentData) {
		JurisdictionalAssignment jurisdictionalAssignment = new JurisdictionalAssignment();
		assignmentData.entrySet().forEach(entry -> {
			Setter<JurisdictionalAssignment> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(jurisdictionalAssignment, entry.getValue());
			}
		});
		return jurisdictionalAssignment;
	}
}
