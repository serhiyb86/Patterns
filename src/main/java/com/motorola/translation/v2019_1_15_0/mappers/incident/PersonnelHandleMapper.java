/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link PersonnelHandle} object for EmergencyIncident model.
 */
public class PersonnelHandleMapper extends GenericMapper<PersonnelHandle> {

	private static final Map<String, Setter<PersonnelHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.PersonnelHandle.KEY, new StringSetter<>(PersonnelHandle::setKey));
		setters.put(InterfaceConstants.Unit.PersonnelHandle.ALIAS, new StringSetter<>(PersonnelHandle::setAlias));
		setters.put(InterfaceConstants.Unit.PersonnelHandle.AGENCY_ALIAS, new StringSetter<>(PersonnelHandle::setAgencyAlias));
		setters.put(InterfaceConstants.Unit.PersonnelHandle.CAD_USER_KEY, new StringSetter<>(PersonnelHandle::setCadUserKey));
	}

	PersonnelHandleMapper() {
		super(setters);
	}

	/**
	 * Creates {@link PersonnelHandle} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link PersonnelHandle} object with mapped data.
	 */
	public PersonnelHandle createAndMapPersonnelHandle(JsonObject data) {
		PersonnelHandle personnelHandle = new PersonnelHandle();
		if (data != null) {
			mapToPersonnelHandle(data, personnelHandle);
		}
		return personnelHandle;
	}

	/**
	 * Sets mapped data from json to the {@link PersonnelHandle} object.
	 *
	 * @param data json data.
	 * @param personnelHandle target object.
	 */
	private void mapToPersonnelHandle(JsonObject data, PersonnelHandle personnelHandle) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(personnelHandle, data.get(key));
			}
		});
	}

}
