/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.RefreshIncidentData;
import com.motorola.translation.setter.BooleanSetter;
import com.motorola.translation.setter.ListSetter;
import com.motorola.translation.setter.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with RefreshIncidentData data to the {@link RefreshIncidentData} object.
 */
public class RefreshIncidentDataMapper {

	private static final Map<String, Setter<RefreshIncidentData>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.RefreshIncidentData.EMERGENCY_INCIDENT_LIST,
			new ListSetter<>(RefreshIncidentData::setEmergencyIncidentList, new EmergencyIncidentMapper(), EmergencyIncident::new));
		setters.put(InterfaceConstants.RefreshIncidentData.IS_FIRST_BATCH_UPDATE, new BooleanSetter<>(RefreshIncidentData::setIsFirstBatchUpdate));
		setters.put(InterfaceConstants.RefreshIncidentData.IS_LAST_BATCH_UPDATE, new BooleanSetter<>(RefreshIncidentData::setIsLastBatchUpdate));
	}

	public RefreshIncidentData createAndMapToRefreshIncidentData(JsonObject refreshUnitDataJson) {
		RefreshIncidentData refreshIncidentData = new RefreshIncidentData();
		refreshUnitDataJson.entrySet().forEach(entry -> {
			Setter<RefreshIncidentData> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(refreshIncidentData, entry.getValue());
			}
		});
		return refreshIncidentData;
	}

}
