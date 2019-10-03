/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Location;
import com.motorola.models.representation.Person;
import com.motorola.translation.setter.LocalDateSetter;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with Person data to the {@link Person} object.
 */
public class PersonMapper {

	private static final Map<String, Setter<Person>> setters = new HashMap<>();
	private static final Map<String, Setter<Person>> driverLicenceSetters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Person.FIRST_NAME, new StringSetter<>(Person::setFirstName));
		setters.put(InterfaceConstants.EmergencyIncident.Person.LAST_NAME, new StringSetter<>(Person::setLastName));
		setters.put(InterfaceConstants.EmergencyIncident.Person.SUFFIX, new StringSetter<>(Person::setSuffix));
		setters.put(InterfaceConstants.EmergencyIncident.Person.MIDDLE_NAME, new StringSetter<>(Person::setMiddleName));
		setters.put(InterfaceConstants.EmergencyIncident.Person.HEIGHT_IN_INCHES, new LongSetter<>(Person::setHeight));
		setters.put(InterfaceConstants.EmergencyIncident.Person.WEIGHT_IN_POUNDS, new LongSetter<>(Person::setWeight));
		setters.put(InterfaceConstants.EmergencyIncident.Person.AGE, new LongSetter<>(Person::setAge));
		setters.put(InterfaceConstants.EmergencyIncident.Person.BIRTH_DATE, new LocalDateSetter<>(Person::setDateOfBirth, InterfaceConstants.GeneralProperties.DATE_FORMAT));
		setters.put(InterfaceConstants.EmergencyIncident.Person.HAIR_COLOR, new StringSetter<>(Person::setHairColorKey));
		setters.put(InterfaceConstants.EmergencyIncident.Person.RACE, new StringSetter<>(Person::setRaceKey));
		setters.put(InterfaceConstants.EmergencyIncident.Person.GENDER, new StringSetter<>(Person::setGenderKey));
		setters.put(InterfaceConstants.EmergencyIncident.Person.PHYSICAL_BUILD, new StringSetter<>(Person::setBuildKey));
		setters.put(InterfaceConstants.EmergencyIncident.Person.EYE_COLOR, new StringSetter<>(Person::setEyeColorKey));
		setters.put(InterfaceConstants.EmergencyIncident.Person.DRIVER_LICENSE, (model, value) -> {
				driverLicenceSetters.put(InterfaceConstants.EmergencyIncident.Person.NUMBER, new StringSetter<>(Person::setDriverLicenseNumber));
				driverLicenceSetters.put(InterfaceConstants.EmergencyIncident.Person.STATE, new StringSetter<>(Person::setDriverLicenseState));
				new GenericMapper<>(driverLicenceSetters).mapToModel((JsonObject) value, model);
			}
		);
		setters.put(InterfaceConstants.EmergencyIncident.Person.PHONES, (model, value) -> {
			JsonArray phonesJsonArray = ((JsonElement) value).getAsJsonArray();
			for (JsonElement element : phonesJsonArray){
				JsonObject phoneObject = element.getAsJsonObject();
				JsonElement phoneNumber = phoneObject.get(InterfaceConstants.EmergencyIncident.Person.Phone.NUMBER);
				String phoneNumberString = CadCloudUtils.getStringFromJsonElement(phoneNumber);
				if (StringUtils.isNotBlank(phoneNumberString)){
					model.setPhone(phoneNumberString);
					break;
				}
			}
		});

		setters.put(InterfaceConstants.EmergencyIncident.Person.LOCATIONS, (model, value) -> {
			JsonArray locationJsonArray = ((JsonElement) value).getAsJsonArray();
			LocationMapper locationMapper = new LocationMapper();
			List<Location> locations = locationMapper.createAndMakeLocationsList(locationJsonArray);
			model.setAddress(locations);
		});
	}

	/**
	 * Creates {@link Person} object and Sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link Person} object with mapped data.
	 */
	public Person createAndMapToPerson(JsonObject data) {
		Person person = new Person();
		return mapToPerson(data, person);
	}

	/**
	 * Sets mapped data from json to the {@link Person} object.
	 *
	 * @param data json data.
	 * @param person target object.
	 * @return filled target object with mapped data.
	 */
	public Person mapToPerson(JsonObject data, Person person) {
		data.entrySet().forEach(entry -> {
			Setter<Person> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(person, entry.getValue());
			}
		});
		return person;
	}

}
