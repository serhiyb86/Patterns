/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Alert;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with Alert data to the {@link Alert} object.
 */
public class AlertMapping {

	private static final Map<String, Setter<Alert>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.COMMENT, new StringSetter<>(Alert::setComment));
	}

	private Alert mapToAlert(JsonObject data) {
		Alert alert = new Alert();
		data.entrySet().forEach(entry -> {
			Setter<Alert> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(alert, entry.getValue());
			}
		});
		return alert;
	}

	public List<Alert> createAndMapToAlertList(JsonArray alerts) {
		List<Alert> involvements = new ArrayList<>();
		for (JsonElement element : alerts) {
			JsonObject jsonObject = element.getAsJsonObject();
			involvements.add(mapToAlert(jsonObject));
		}
		return involvements;
	}

}
