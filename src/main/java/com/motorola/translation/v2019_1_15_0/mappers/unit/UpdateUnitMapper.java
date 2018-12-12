/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.UpdateUnit;
import com.motorola.translation.setter.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with UpdateUnit data to the {@link UpdateUnit} object.
 */
public class UpdateUnitMapper {

	private static final Map<String, Setter<UpdateUnit>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.GeneralProperties.OLD_JSON_KEY, ((model, value) -> {
			JsonObject oldUnitData = ((JsonElement) value).getAsJsonObject();
			UnitMapper unitMapper = new UnitMapper();
			IncidentHandleMapper incidentHandleMapper = new IncidentHandleMapper();
			model.setOld(unitMapper.createAndMapToUnit(oldUnitData));
			model.getOld().setAssignedIncident(incidentHandleMapper.createAndMapToIncidentHandle(oldUnitData));
		}));

		setters.put(InterfaceConstants.Unit.GeneralProperties.NEW_JSON_KEY, ((model, value) -> {
			JsonObject newUnitData = ((JsonElement) value).getAsJsonObject();
			UnitMapper unitMapper = new UnitMapper();
			IncidentHandleMapper incidentHandleMapper = new IncidentHandleMapper();
			model.set__new(unitMapper.createAndMapToUnit(newUnitData));
			model.get__new().setAssignedIncident(incidentHandleMapper.createAndMapToIncidentHandle(newUnitData));
		}));
	}

	public UpdateUnit createAndMapToUpdateUnit(JsonObject updateUnitData) {
		UpdateUnit updateUnit = new UpdateUnit();
		updateUnitData.entrySet().forEach(entry -> {
			Setter<UpdateUnit> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(updateUnit, entry.getValue());
			}
		});
		return updateUnit;
	}

}


