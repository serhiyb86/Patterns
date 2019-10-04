/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Equipment;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Unit data to the {@link Equipment} object
 */
class EquipmentMapper extends GenericMapper<Equipment> {

	private static final Map<String, Setter<Equipment>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.Equipment.AGENCY_KEY, new StringSetter<>(Equipment::setAgencyKey));
		setters.put(InterfaceConstants.Unit.Equipment.EQUIPMENT_ITEM_NAME, new StringSetter<>(Equipment::setEquipmentItemName));
		setters.put(InterfaceConstants.Unit.Equipment.KEY, new StringSetter<>(Equipment::setKey));
		setters.put(InterfaceConstants.Unit.Equipment.TYPE_KEY, new StringSetter<>(Equipment::setTypeKey));
	}

	EquipmentMapper() {
		super(setters);
	}

}
