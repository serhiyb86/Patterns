/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Location;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Location data to the {@link Location} object.
 */
public class LocationMapper {

	private static final Map<String, Setter<Location>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.TYPE, new StringSetter<>(Location::setType));
	}

	public Location createAndMapLocation(JsonObject data, Jurisdiction jurisdiction) {
		Location location = new Location();

		data.entrySet().forEach(entry -> {
			Setter<Location> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(location, entry.getValue());
			}
		});

		JsonObject addressObject = CadCloudUtils.getJsonByKey(data, InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ADDRESS);
		AddressMapper addressMapper = new AddressMapper();
		Address address = addressMapper.createAndMapToAddress(addressObject, jurisdiction);
		location.setAddress(address);

		//static value for Incident
		location.setKey("1");
		return location;
	}

}
