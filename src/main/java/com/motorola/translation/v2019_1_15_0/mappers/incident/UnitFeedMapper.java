/*
 * Copyright 2020 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.models.representation.UnitFeed;
import com.motorola.translation.setter.ListSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link UnitFeed} object for EmergencyIncident model.
 */
public class UnitFeedMapper {

	private static final Map<String, Setter<UnitFeed>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.KEY, new StringSetter<>(UnitFeed::setKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.AGENCY_KEY, new StringSetter<>(UnitFeed::setAgencyKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.AGENCY_ALIAS, new StringSetter<>(UnitFeed::setAgencyAlias));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.CALL_SIGN, new StringSetter<>(UnitFeed::setCallSign));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.SHIFT_ID, new StringSetter<>(UnitFeed::setShiftId));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.HOME_AREA_KEY, new StringSetter<>(UnitFeed::setHomeAreaKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.HOME_STATION_KEY, new StringSetter<>(UnitFeed::setHomeStationKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.ASSIGNED_PERSONNEL, new ListSetter<>(UnitFeed::setAssignedPersonnel, new PersonnelHandleMapper(), PersonnelHandle::new));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.STATUS_KEY, new StringSetter<>(UnitFeed::setStatusKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.WHEN_STATUS_DECLARED, new StringSetter<>(UnitFeed::setWhenStatusDeclared));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.UnitFeed.STATUS_CATEGORY_KEY, new StringSetter<>(UnitFeed::setStatusCategoryKey));
	}

	/**
	 * Creates {@link UnitFeed} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link UnitFeed} object with mapped data.
	 */
	public UnitFeed createAndMapUnitFeed(JsonObject data) {
		UnitFeed unitFeed = new UnitFeed();
		if (data != null) {
			mapToUnitFeed(data, unitFeed);
		}
		return unitFeed;
	}

	/**
	 * Sets mapped data from json to the {@link UnitFeed} object.
	 *
	 * @param data json data.
	 * @param unitFeed target object.
	 */
	private static void mapToUnitFeed(JsonObject data, UnitFeed unitFeed) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(unitFeed, data.get(key));
			}
		});
	}
}
