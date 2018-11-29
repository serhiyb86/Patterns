/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.models.representation.InvolvedVehicle;
import com.motorola.models.representation.Vehicle;
import com.motorola.translation.setter.Setter;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with InvolvedVehicles data to the {@link InvolvedVehicle} object.
 */
public class InvolvedVehicleMapper {

	private static final Map<String, Setter<InvolvedVehicle>> setters = new HashMap<>();

	static{
	}

	public List<InvolvedVehicle> createAndMapToInvolvedVehicleList(JsonArray involvedVehicles) {
		List<InvolvedVehicle> result = new ArrayList<>();
		VehicleMapper vehicleMapper = new VehicleMapper();
		//List<Vehicle> vehicles = vehicleMapper.createAndMapToVehicleList(involvedVehicles);
		return null;
	}
}
