/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

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
import java.util.Set;

/**
 * Mapper for converting Json Object with Subject data to the {@link Subject} object.
 */
public class SubjectMapper {

	private static final Map<String, Setter<Subject>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Subject.ROLE, (model, value) ->
			model.setRole(Collections.singletonList(CadCloudUtils.getStringFromJsonElement((JsonElement) value)))
		);
		setters.put(InterfaceConstants.EmergencyIncident.Subject.SUBJECT, (model, value) -> {
			JsonObject subjectObject = ((JsonElement) value).getAsJsonObject();
			JsonObject jsonPerson = CadCloudUtils.getJsonByKey(subjectObject, InterfaceConstants.EmergencyIncident.Person.PERSON);
			Person person = new PersonMapper().createAndMapToPerson(jsonPerson.entrySet());
			if (StringUtils.isNotBlank(person.getLastName())) {
				model.setPerson(person);
			}
		});
	}

	private void mapToSubject(Set<Map.Entry<String, JsonElement>> data, Subject subject) {
		data.forEach(entry -> {
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
			JsonObject jsonObject = element.getAsJsonObject();
			Subject subject = new Subject();
			subject.setContactRequested("No");
			mapToSubject(jsonObject.entrySet(), subject);
			List<String> role = subject.getRole();
			// add subjects only with particular role.
			if (!role.isEmpty() && "Complainant".equals(role.get(0)) || "ReportingParty".equals(role.get(0))) {
				subjects.add(subject);
			}
		}
		return subjects;
	}

}
