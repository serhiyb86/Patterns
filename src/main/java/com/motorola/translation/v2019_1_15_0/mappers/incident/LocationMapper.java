/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Location;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with Location data to the {@link Location} object.
 */
public class LocationMapper {

	private static final Map<String, Setter<Location>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.TYPE, new StringSetter<>(Location::setType));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ID, new StringSetter<>(Location::setKey));
	}

	/**
	 * Sets mapped data from json to the {@link Location} object.
	 *
	 * @param data json data.
	 * @param location target object.
	 * @return filled target object with mapped data.
	 */
	public Location mapToLocation(JsonObject data, Location location) {
		setters.forEach((key, consumer) -> {
			if (data.get(key) != null) {
				consumer.accept(location, data.get(key));
			}
		});
		if (StringUtils.isBlank(location.getKey())){
			location.setKey(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ID_DEFAULT_VALUE);
		}
		return location;
	}

	/**
	 * Creates {@link Location} object and Sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link Location} object with mapped data.
	 */
	public Location createAndMapLocation(JsonObject data) {
		Location location = new Location();
		location = mapToLocation(data, location);

		JsonObject addressObject = CadCloudUtils.getJsonByKey(data, InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ADDRESS);
		AddressMapper addressMapper = new AddressMapper();
		Address address = addressMapper.createAndMapToAddress(addressObject);
		location.setAddress(address);
		return location;
	}

	public Location createAndMapLocation(JsonObject data, Jurisdiction jurisdiction) {
		Location location = new Location();
		location = mapToLocation(data, location);

		JsonObject addressObject = CadCloudUtils.getJsonByKey(data, InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ADDRESS);
		AddressMapper addressMapper = new AddressMapper();
		Address address = addressMapper.createAndMapToAddress(addressObject, jurisdiction);
		location.setAddress(address);

		//static value for Incident
		location.setKey("1");
		return location;
	}

	/**
	 * Creates {@link List<Location>} instance from the incoming {@link JsonArray} object.
	 *
	 * @param data {@link JsonArray} instance.
	 * @return {@link List<Location>} instance.
	 */
	public List<Location> createAndMakeLocationsList(JsonArray data) {
		List<Location> locations = new ArrayList<>();
		for (JsonElement element : data) {
			JsonObject locationObject = element.getAsJsonObject();
			Location location = createAndMapLocation(locationObject);
			locations.add(location);
		}
		return locations;
	}

}
