/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.incident;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Vehicle;
import com.motorola.translation.setter.ApiDateSetter;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1.mappers.AbstractMapper;

import java.util.HashMap;

import java.util.Map;

/**
 * Mapper for converting Json Object with InvolvedVehicles data to the {@link Vehicle} object.
 */
public class VehicleMapper extends AbstractMapper {

	private static final Map<String, Setter<Vehicle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_PLATE, new StringSetter<>(Vehicle::setLicensePlate));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_STATE, new StringSetter<>(Vehicle::setLicenseState));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_TYPE, (model, value) -> model.setLicenseType(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENSE_EXPIRATION, new ApiDateSetter<>(Vehicle::setLicenseExpirationDate));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.YEAR, new LongSetter<>(Vehicle::setYear));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.MAKE, (model, value) -> model.setMake(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.MODEL, (model, value) -> model.setModel(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.PRIMARY_COLOR, (model, value) -> model.setPrimaryColor(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.SECONDARY_COLOR, (model, value) -> model.setSecondaryColor(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.VIN, new StringSetter<>(Vehicle::setVin));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.OWNER, new StringSetter<>(Vehicle::setOwner));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.COMMENT, new StringSetter<>(Vehicle::setComment));
	}

	/**
	 *  Fills the {@link Vehicle} object
	 * @param data json data
	 * @return the {@link Vehicle}object
	 */
	public Vehicle createVehicle(JsonObject data) {
		Vehicle vehicle = new Vehicle();
		data.entrySet().forEach(entry -> {
			Setter<Vehicle> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(vehicle, entry.getValue());
			}
		});
		return vehicle;
	}

}
