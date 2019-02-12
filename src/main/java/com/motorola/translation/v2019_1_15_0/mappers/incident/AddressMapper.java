/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.translation.setter.MultipleFieldsStringSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.AbstractMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Address data to the {@link Address} object.
 */
public class AddressMapper extends AbstractMapper {

	private static final Map<String, Setter<Address>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.STREET_FULL_TEXT, new MultipleFieldsStringSetter<>(Address::setFullText, Address::setDescription));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.CROSS_STREET, new StringSetter<>(Address::setCrossStreet));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.CITY, new StringSetter<>(Address::setCity));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.STATE, new StringSetter<>(Address::setState));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ZIP, new StringSetter<>(Address::setZip));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.LATITUDE, new StringSetter<>(Address::setLatitude));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.LONGITUDE, new StringSetter<>(Address::setLongitude));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ID, new MultipleFieldsStringSetter<>(
			(model, value) -> model.setIsVerified(StringUtils.isNotEmpty(value)),
			(model, value) -> model.setGeoverificationLevel(StringUtils.isEmpty(value) ? "0" : "100")));
	}

	/**
	 * Maps Json data into single {@link Address} object.
	 *
	 * @param data json data
	 * @return {@link Address} object
	 */
	public Address createAndMapToAddress(JsonObject data, Jurisdiction jurisdiction) {
		final Address address = new Address();
		setters.forEach((key, consumer)->{
			if (data.get(key)!= null) {
				consumer.accept(address, data.get(key));
			}
		});

		// Jurisdiction object consist of data that isn't contained in "Address" object in incoming json
		// so it has to be received from parent object directly
		address.setJurisdiction(jurisdiction);
		return address;
	}

}
