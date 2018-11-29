/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.InvolvedVehicle;
import com.motorola.models.representation.Vehicle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for converting Json Object with InvolvedVehicles data to the {@link InvolvedVehicle} object.
 */
public class InvolvedVehicleMapper {

	private static final Map<String, Setter<InvolvedVehicle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.INVOLVED_KEY, new StringSetter<>(InvolvedVehicle::setKey));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.INVOLVED_RELATION, (model, value) ->
			model.setRole(Collections.singletonList(CadCloudUtils.getStringFromJsonElement((JsonElement) value)))
		);
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.INVOLVED_VEHICLE, (model, value) -> {
			JsonObject jsonVehicle = ((JsonElement) value).getAsJsonObject();
			Vehicle vehicle = new VehicleMapper().createVehicle(jsonVehicle.entrySet());
			model.setVehicle(vehicle);
		});
	}

	private InvolvedVehicle mapToInvolvedVehicle(Set<Map.Entry<String, JsonElement>> data) {
		InvolvedVehicle involvedVehicle = new InvolvedVehicle();
		data.forEach(entry -> {
			Setter<InvolvedVehicle> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(involvedVehicle, entry.getValue());
			}
		});
		return involvedVehicle;
	}

	public List<InvolvedVehicle> createAndMapToInvolvedVehicleList(JsonArray involvedVehicles) {
		List<InvolvedVehicle> involvements = new ArrayList<>();
		for (JsonElement element : involvedVehicles) {
			JsonObject jsonObject = element.getAsJsonObject();
			involvements.add(mapToInvolvedVehicle(jsonObject.entrySet()));
		}
		return involvements;
	}
}
