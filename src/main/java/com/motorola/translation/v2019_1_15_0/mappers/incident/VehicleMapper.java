/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Vehicle;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with InvolvedVehicles data to the {@link Vehicle} object.
 */
public class VehicleMapper {

	private static final Map<String, Setter<Vehicle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_PLATE, new StringSetter<>(Vehicle::setLicensePlate));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_STATE, (vehicle, value) -> {
			String licenseState = ((JsonElement) value).getAsString();
			vehicle.setLicenseState(licenseState);
			vehicle.setLicenseStateKey(licenseState);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_TYPE, new StringSetter<>(Vehicle::setLicenseTypeKey));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENSE_EXPIRATION, new StringSetter<>(Vehicle::setLicenseExpirationDate));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.YEAR, new LongSetter<>(Vehicle::setYear));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.MAKE, ((vehicle, value) -> {
			String make = ((JsonElement) value).getAsString();
			vehicle.setMakeKey(make);
			vehicle.setMakeAlias(make);
		}));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.MODEL, ((vehicle, value) -> {
			String model = ((JsonElement) value).getAsString();
			vehicle.setModelKey(model);
			vehicle.setModelAlias(model);
		}));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.PRIMARY_COLOR, new StringSetter<>(Vehicle::setPrimaryColorKey));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.SECONDARY_COLOR, new StringSetter<>(Vehicle::setSecondaryColorKey));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.VIN, new StringSetter<>(Vehicle::setVin));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.OWNER, new StringSetter<>(Vehicle::setOwner));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.COMMENT, new StringSetter<>(Vehicle::setComment));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.TYPE, new StringSetter<>(Vehicle::setStyleKey));
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