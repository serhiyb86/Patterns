/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Unit data to the {@link PersonnelHandle} object.
 */
class PersonnelHandleMapper extends GenericMapper<PersonnelHandle> {

	private static final Map<String, Setter<PersonnelHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.PersonnelHandle.KEY, new StringSetter<>(PersonnelHandle::setKey));
		setters.put(InterfaceConstants.Unit.PersonnelHandle.ALIAS, new StringSetter<>(PersonnelHandle::setAlias));
		setters.put(InterfaceConstants.Unit.PersonnelHandle.AGENCY_ALIAS, new StringSetter<>(PersonnelHandle::setAgencyAlias));
	}

	PersonnelHandleMapper() {
		super(setters);
	}

}
