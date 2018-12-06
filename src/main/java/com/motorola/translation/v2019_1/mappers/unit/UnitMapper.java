/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.unit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.JurisdictionalAssignment;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.Unit;
import com.motorola.translation.setter.BooleanSetter;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Unit data to the {@link Unit} object.
 */
public class UnitMapper {

	private static final Map<String, Setter<Unit>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT, (model, value) -> {
			String id = ((JsonElement) value).getAsString();
			model.setKey(id);
			model.setCallSign(id);
		});
		setters.put(InterfaceConstants.Unit.GeneralProperties.CUSTOMER_ID, new StringSetter<>(Unit::setCustomerId));
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_AGENCY, new StringSetter<>(Unit::setAgency));
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_STATUS_CODE, new StringSetter<>(Unit::setStatus));
		setters.put(InterfaceConstants.Unit.GeneralProperties.WHEN_STATUS_DECLARED,
			new StringSetter<>(Unit::setWhenStatusDeclared));
		setters.put(InterfaceConstants.Unit.GeneralProperties.MINIMUM_STAFFING_LEVEL, new LongSetter<>(Unit::setMinimumStaffingLevel));
		setters.put(InterfaceConstants.Unit.GeneralProperties.IS_PERSONNEL_OUTSIDE_UNIT, new BooleanSetter<>(Unit::setIsPersonnelOutsideUnit));

		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_ZONE, (model, value) -> {
			String assignmentData = ((JsonElement) value).getAsString();
			JurisdictionalAssignment jurisdictionalAssignment = new JurisdictionalAssignment();
			Lookup lookup = new Lookup();
			lookup.setUid(assignmentData);
			jurisdictionalAssignment.setBeats(new ArrayList<>(Arrays.asList(lookup)));
			model.setJurisdictionalAssignment(jurisdictionalAssignment);
		});
	}

	public Unit createAndMapToUnit(JsonObject unitData) {
		Unit unit = new Unit();
		unitData.entrySet().forEach(entry -> {
			Setter<Unit> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(unit, entry.getValue());
			}
		});
		return unit;
	}
}
