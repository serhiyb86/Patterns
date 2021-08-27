/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.Alert;
import com.motorola.translation.setter.LocalDateSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with Alert data to the {@link Alert} object.
 */
public class AlertMapper {

	private static final Map<String, Setter<Alert>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.TYPE, new StringSetter<>(Alert::setTypeKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.ALERT,  new StringSetter<>(Alert::setCategoryKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.COMMENT, ((model, value) -> {
			String comment = ((JsonElement) value).getAsString();
			// comment and title mapped to one incoming value
			model.setTitle(comment);
			model.setComment(comment);
		}));
		// the level field doesn't receive from API ( will be hardcoded for default value '0'
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.LEVEL, new StringSetter<>(Alert::setPriority));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.START_DATE, new LocalDateSetter<>(Alert::setWhenCreated, InterfaceConstants.GeneralProperties.DATE_FORMAT));
		// For now there is no such field in Alert model description (need further clarification)
		// setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.Alert.END_DATE, new StringSetter<>(Alert::setWhenExpired));
	}

	private static Alert mapToAlert(JsonObject data) {
		Alert alert = new Alert();
		setters.forEach((key, consumer)->{
			if (data.get(key)!= null) {
				consumer.accept(alert, data.get(key));
			}
		});
		// set default value '0' for priority
		alert.setPriority("0");
		return alert;
	}

	/***
	 * Create the list of alerts
	 * @param addressAlerts - json object with address and alerts information
	 * @returnlist of alerts
	 */
	public List<Alert> createAndMapToAlertList(JsonObject addressAlerts) {
		JsonArray alerts = CadCloudUtils.getJsonArrayByKey(addressAlerts, InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ALERTS);
		List<Alert> involvements = new ArrayList<>();
		for (JsonElement element : alerts) {
			JsonObject jsonObject = element.getAsJsonObject();
			Alert alert = mapToAlert(jsonObject);
			// Map Address to Alert
			AddressMapper addressMapper = new AddressMapper();
			Address addressModel = addressMapper.createAndMapToAddress(addressAlerts);
			alert.setAddress(addressModel);
			involvements.add(alert);
		}
		return involvements;
	}

}
