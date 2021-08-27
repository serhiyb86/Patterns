/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link PersonnelHandle} object for BookOn model.
 */
public class PersonnelHandleMapper {

	private static final Map<String, Setter<PersonnelHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.CAD_USER_KEY, new StringSetter<>(PersonnelHandle::setKey));
		setters.put(InterfaceConstants.BookOnProperties.CAD_USER_ALIAS, new StringSetter<>(PersonnelHandle::setAlias));
		setters.put(InterfaceConstants.BookOnProperties.CAD_USER_AGENCY_ALIAS, new StringSetter<>(PersonnelHandle::setAgencyAlias));
		setters.put(InterfaceConstants.BookOnProperties.CAD_USERS_KEY, new StringSetter<>(PersonnelHandle::setCadUserKey));
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
	private static void mapToPersonnelHandle(JsonObject data, PersonnelHandle personnelHandle) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(personnelHandle, data.get(key));
			}
		});
	}

}
