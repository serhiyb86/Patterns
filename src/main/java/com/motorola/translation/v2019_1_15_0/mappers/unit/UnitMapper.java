/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Equipment;
import com.motorola.models.representation.JurisdictionalAssignment;
import com.motorola.models.representation.Personnel;
import com.motorola.models.representation.Unit;
import com.motorola.models.representation.GpsData;
import com.motorola.translation.setter.BooleanSetter;
import com.motorola.translation.setter.ListSetter;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.setter.ObjectSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with Unit data to the {@link Unit} object
 * including nested objects and objects lists.
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
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_AGENCY, new StringSetter<>(Unit::setAgencyKey));
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_STATUS_CODE, new StringSetter<>(Unit::setStatusKey));
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_TYPE_CODE, new StringSetter<>(Unit::setDisciplineKey));
		setters.put(InterfaceConstants.Unit.GeneralProperties.WHEN_STATUS_DECLARED, new StringSetter<>(Unit::setWhenStatusDeclared));
		setters.put(InterfaceConstants.Unit.GeneralProperties.MINIMUM_STAFFING_LEVEL, new LongSetter<>(Unit::setMinimumStaffingLevel));
		setters.put(InterfaceConstants.Unit.GeneralProperties.IS_PERSONNEL_OUTSIDE_UNIT, new BooleanSetter<>(Unit::setIsPersonnelOutsideUnit));
		setters.put(InterfaceConstants.Unit.GeneralProperties.SHIFT_ID, new StringSetter<>(Unit::setShiftId));
		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_DESCRIPTION, new StringSetter<>(Unit::setUnitDescription));

		setters.put(InterfaceConstants.Unit.GeneralProperties.EQUIPMENT,
			new ListSetter<>(Unit::setEquipment, new EquipmentMapper(), Equipment::new));
		setters.put(InterfaceConstants.Unit.GeneralProperties.ASSIGNED_PERSONNEL,
			new ListSetter<>(Unit::setAssignedPersonnel, new PersonnelMapper(), Personnel::new));

		setters.put(InterfaceConstants.Unit.GeneralProperties.UNIT_ZONE, (model, value) -> {
			String assignmentData = ((JsonElement) value).getAsString();
			List<String> monitoringAreas = new ArrayList<>();
			monitoringAreas.add(assignmentData);
			JurisdictionalAssignment jurisdictionalAssignment = new JurisdictionalAssignment();
			jurisdictionalAssignment.setBeatKeys(monitoringAreas);
			jurisdictionalAssignment.setAreaKeys(monitoringAreas);
			model.setJurisdictionalAssignment(jurisdictionalAssignment);
		});

		setters.put(InterfaceConstants.Unit.GeneralProperties.CURRENT_GPS_DATA, new ObjectSetter<>(Unit::setCurrentGpsData, new GpsDataMapper(), GpsData::new));
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
