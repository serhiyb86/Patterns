/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.RefreshUnitData;
import com.motorola.models.representation.Unit;
import com.motorola.models.serializer.ZonedDateTimeSerializer;
import com.motorola.translation.setter.BooleanSetter;
import com.motorola.translation.setter.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with RefreshUnitData data to the {@link RefreshUnitData} object.
 */
public class RefreshUnitsDataMapper {

	private static final Map<String, Setter<RefreshUnitData>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.RefreshUnitData.UNIT_LIST, ((model, value) -> {
			List<Unit> units = new ArrayList<>();
			JsonArray jsonArray = ((JsonElement) value).getAsJsonArray();
			UnitMapper unitMapper = new UnitMapper();
			for (JsonElement element : jsonArray) {
				Unit unit = unitMapper.createAndMapToUnit(element.getAsJsonObject());
				unit.setWhenUpdated(ZonedDateTimeSerializer.DATE_TIME_FORMATTER.format(ZonedDateTime.now()));
				unit.setAssignedIncident(new IncidentHandleMapper().createAndMapToIncidentHandle(element.getAsJsonObject()));
				units.add(unit);
			}
			model.setUnitList(units);
		}));
		setters.put(InterfaceConstants.RefreshUnitData.IS_FIRST_BATCH_UPDATE, new BooleanSetter<>(RefreshUnitData::setIsFirstBatchUpdate));
		setters.put(InterfaceConstants.RefreshUnitData.IS_LAST_BATCH_UPDATE, new BooleanSetter<>(RefreshUnitData::setIsLastBatchUpdate));
	}

	public RefreshUnitData createAndMapToRefreshUnitData(JsonObject refreshUnitDataJson) {
		RefreshUnitData refreshUnitData = new RefreshUnitData();
		refreshUnitDataJson.entrySet().forEach(entry -> {
			Setter<com.motorola.models.representation.RefreshUnitData> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(refreshUnitData, entry.getValue());
			}
		});
		return refreshUnitData;
	}
}
