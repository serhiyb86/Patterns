/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Person;
import com.motorola.models.representation.Subject;
import com.motorola.translation.setter.Setter;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with Subject data to the {@link Subject} object.
 */
public class SubjectMapper {

	private static final Map<String, Setter<Subject>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Subject.ROLE_KEY, (model, value) -> {
			model.setRoleKey(Collections.singletonList(CadCloudUtils.getStringFromJsonElement((JsonElement) value)));
		});
		setters.put(InterfaceConstants.EmergencyIncident.Subject.NestedSubject.NESTED_SUBJECT, (model, value) -> {
			JsonObject subjectObject = ((JsonElement) value).getAsJsonObject();
			//Map ID
			model.setKey(CadCloudUtils.getStringByKey(subjectObject, InterfaceConstants.EmergencyIncident.Subject.NestedSubject.ID));
			//Map Person
			JsonObject jsonPerson = CadCloudUtils.getJsonByKey(subjectObject, InterfaceConstants.EmergencyIncident.Person.PERSON);
			if (jsonPerson != null) {
				Person person = new PersonMapper().createAndMapToPerson(jsonPerson);
				if (StringUtils.isNotBlank(person.getLastName())) {
					model.setPerson(person);
				}
			}
		});
		setters.put(InterfaceConstants.EmergencyIncident.Person.PERSON, (model, value) -> {
			JsonObject jsonPerson = ((JsonElement) value).getAsJsonObject();
			if (jsonPerson != null) {
				Person person = new PersonMapper().createAndMapToPerson(jsonPerson);
				if (StringUtils.isNotBlank(person.getLastName())) {
					model.setPerson(person);
				}
			}
		});
	}

	private void mapToSubject(JsonObject data, Subject subject) {
		data.entrySet().forEach(entry -> {
			Setter<Subject> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(subject, entry.getValue());
			}
		});
	}

	/**
	 * Creates {@link List<Subject>} instance from the incoming {@link JsonArray} object.
	 *
	 * @param array {@link JsonArray} instance.
	 * @return {@link List<Subject>} instance.
	 */
	public List<Subject> createAndMapToSubjectList(JsonArray array) {
		List<Subject> subjects = new ArrayList<>();
		for (JsonElement element : array) {
			Subject subject = createAndMapToSubject(element);
			List<String> roleKey = subject.getRoleKey();
			// add subjects only with particular role.
			if (!roleKey.isEmpty() && "Complainant".equals(roleKey.get(0)) || "ReportingParty".equals(roleKey.get(0))) {
				subjects.add(subject);
			}
		}
		return subjects;
	}

	/**
	 * Creates {@link Subject} instance from the incoming {@link JsonElement} object.
	 */
	public Subject createAndMapToSubject(JsonElement element) {
		JsonObject jsonObject = element.getAsJsonObject();
		Subject subject = new Subject();
		subject.setContactRequested("No");
		mapToSubject(jsonObject, subject);
		return subject;
	}

}
