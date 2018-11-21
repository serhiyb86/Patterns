/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.Person;
import com.motorola.translation.setter.DateSetter;
import com.motorola.translation.setter.LongSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
		setters.put(InterfaceConstants.EmergencyIncident.Person.HEIGHT_IN_INCHES, new StringSetter<>(Person::setHeight));
		setters.put(InterfaceConstants.EmergencyIncident.Person.WEIGHT_IN_POUNDS, new LongSetter<>(Person::setWeight));
		setters.put(InterfaceConstants.EmergencyIncident.Person.AGE, new LongSetter<>(Person::setAge));
		setters.put(InterfaceConstants.EmergencyIncident.Person.BIRTH_DATE, new DateSetter<>(Person::setDateOfBirth, InterfaceConstants.EmergencyIncident.GeneralProperties.DATE_FORMAT));
		setters.put(InterfaceConstants.EmergencyIncident.Person.HAIR_COLOR, ((model, value) -> model.setHairColor(createLookup((JsonElement) value))));
		setters.put(InterfaceConstants.EmergencyIncident.Person.RACE, (model, value) -> model.setRace(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Person.GENDER, (model, value) -> model.setGender(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Person.PHYSICAL_BUILD, (model, value) -> model.setBuild(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Person.EYE_COLOR, (model, value) -> model.setEyeColor(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Person.DRIVER_LICENSE, (model, value) -> {
				driverLicenceSetters.put(InterfaceConstants.EmergencyIncident.Person.NUMBER, new StringSetter<>(Person::setDriverLicenseNumber));
				driverLicenceSetters.put(InterfaceConstants.EmergencyIncident.Person.STATE, new StringSetter<>(Person::setDriverLicenseState));
				new GenericMapper<>(driverLicenceSetters).mapToModel((JsonObject) value, model);
			}
		);
	}

	/**
	 * Creates {@link Lookup} instance with incoming data.
	 *
	 * @param value incoming data.
	 * @return {@link Lookup} instance.
	 */
	private static Lookup createLookup(JsonElement value) {
		Lookup lookup = new Lookup();
		if (value != null) {
			lookup.setUid(value.getAsString());
		}
		return lookup;
	}

	/**
	 * Creates {@link Person} object and Sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link Person} object with mapped data.
	 */
	public Person createAndMapToPerson(Set<Map.Entry<String, JsonElement>> data) {
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
	public Person mapToPerson(Set<Map.Entry<String, JsonElement>> data, Person person) {
		data.forEach(entry -> {
			Setter<Person> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(person, entry.getValue());
			}
		});
		return person;
	}

}
