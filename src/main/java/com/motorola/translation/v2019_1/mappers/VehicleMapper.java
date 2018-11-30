/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

import com.google.gson.JsonElement;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Vehicle;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.HashMap;

import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for converting Json Object with InvolvedVehicles data to the {@link Vehicle} object.
 */
public class VehicleMapper extends AbstractMapper {

	private static final Map<String, Setter<Vehicle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_PLATE, new StringSetter<>(Vehicle::setLicensePlate));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_STATE, new StringSetter<>(Vehicle::setLicenseState));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENCE_TYPE, (model, value) -> model.setLicenseType(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.LICENSE_EXPIRATION, (model, value) -> model.setLicenseExpirationDate(formatDate((JsonElement) value)));
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
	 * Format date for accept String in CAD Cloud models ( )
	 * @param value - incoming date string json value
	 */
	private static String formatDate(JsonElement value) {
		String result = "";
		// incoming format
		DateTimeFormatter incomingFormatter = DateTimeFormatter.ofPattern(InterfaceConstants.EmergencyIncident.GeneralProperties.DATE_FORMAT);
		// result format for date
		DateTimeFormatter outgoingFormatter = DateTimeFormatter.ofPattern(InterfaceConstants.EmergencyIncident.GeneralProperties.ZONED_DATE_TIME_FORMAT);
		if (value != null) {
			LocalDate localDate = LocalDate.parse(value.getAsString(), incomingFormatter);
			LocalDateTime localDateTime = localDate.atStartOfDay();
			result = outgoingFormatter.format(localDateTime);
		}
		return result;
	}

	/**
	 *  Fills the {@link Vehicle} object
	 * @param data json data
	 * @return the {@link Vehicle}object
	 */
	public Vehicle createVehicle(Set<Map.Entry<String, JsonElement>> data) {
		Vehicle vehicle = new Vehicle();
		data.forEach(entry -> {
			Setter<Vehicle> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(vehicle, entry.getValue());
			}
		});
		return vehicle;
	}

}
