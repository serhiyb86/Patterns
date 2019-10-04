/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Equipment;
import com.motorola.models.representation.Personnel;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.translation.setter.ListSetter;
import com.motorola.translation.setter.ObjectSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Unit data to the {@link Personnel} object
 * including nested objects and objects lists.
 */
class PersonnelMapper extends GenericMapper<Personnel> {

	private static final Map<String, Setter<Personnel>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.Personnel.PERSONNEL_HANDLE,
			new ObjectSetter<>(Personnel::setPersonnelHandle, new PersonnelHandleMapper(), PersonnelHandle::new));
		setters.put(InterfaceConstants.Unit.Personnel.EQUIPMENT,
			new ListSetter<>(Personnel::setEquipment, new EquipmentMapper(), Equipment::new));
	}

	PersonnelMapper() {
		super(setters);
	}

}
