/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;

/**
 * Mapper for converting Json Object with Address data to the {@link Address} object.
 */
public class AddressMapper {

	private static final String NO_GEOVALID = "NOGEO";
	public static final String ADDRESS_DELIMITER = "bldg#";

	private static final Map<String, Setter<Address>> setters = new LinkedHashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.STREET_FULL_TEXT, (model, value) -> {
			String fullText = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
			if (fullText.contains(ADDRESS_DELIMITER)) {
				String[] address = fullText.split(ADDRESS_DELIMITER);
				if (address.length == 2) {
					model.setBuilding(address[1].trim());
				}
				fullText = address[0].trim();
			}
			model.setFullText(fullText);
			model.setDescription(fullText);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.CROSS_STREET, new StringSetter<>(Address::setCrossStreet));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.CITY, new StringSetter<>(Address::setCity));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.STATE, new StringSetter<>(Address::setState));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ZIP, new StringSetter<>(Address::setZip));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.LATITUDE, new StringSetter<>(Address::setLatitude));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.LONGITUDE, new StringSetter<>(Address::setLongitude));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.INTERSECTION, new StringSetter<>(Address::setIntersection));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.LOCATION_DESCRIPTION, new StringSetter<>(Address::setCommonPlace));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ID, (model, value) -> {
			String idValue = CadCloudUtils.getStringFromJsonElement((JsonElement) value);
			model.setKey(idValue);
			if (StringUtils.isNotBlank(idValue) && !NO_GEOVALID.equals(idValue)) {
				//If address is geo-valid
				model.setIsVerified(true);
				model.setGeoverificationLevel("100");
			} //otherwise - default values remain
		});

		//It can be used in the future during the implementation of PremiseHazards endpoint
		/*setters.put(InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ALERTS, (model, value) -> {
			AlertMapper alertMapping = new AlertMapper();
			JsonArray alerts = ((JsonElement) value).getAsJsonArray();
			model.setAlerts(alertMapping.createAndMapToAlertList(alerts));
		});*/
	}

	/**
	 * Maps Json data into single {@link Address} object.
	 *
	 * @param data json data
	 * @return {@link Address} object
	 */
	public Address createAndMapToAddress(JsonObject data) {
		final Address address = new Address();
		if (data != null) {
			//Set default values
			address.setIsVerified(false);
			address.setGeoverificationLevel("0");
			setters.forEach((key, consumer) -> {
				if (data.get(key) != null) {
					consumer.accept(address, data.get(key));
				}
			});
		}
		return address;
	}

	/**
	 * Maps Json data into single {@link Address} object and sets jurisdiction value.
	 *
	 * @param data         json data
	 * @param jurisdiction {@link Jurisdiction} instance
	 * @return {@link Address} object
	 */
	public Address createAndMapToAddress(JsonObject data, Jurisdiction jurisdiction) {
		final Address address = createAndMapToAddress(data);

		// Jurisdiction object consist of data that isn't contained in "Address" object in incoming json
		// so it has to be received from parent object directly
		address.setJurisdiction(jurisdiction);
		return address;
	}

}
