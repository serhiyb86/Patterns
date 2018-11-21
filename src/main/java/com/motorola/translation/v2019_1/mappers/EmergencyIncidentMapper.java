/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.Subject;
import com.motorola.translation.setter.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EmergencyIncidentMapper {

	private static final Map<String, Setter<EmergencyIncident>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.GeneralProperties.ID_JSON_KEY, (model, value) -> {
			String id = ((JsonElement) value).getAsString();
			model.setId(id);
			model.setKey(id);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Subject.INVOLVED_CAD_SUBJECTS, (model, value) -> {
			JsonArray involvedCADSubjects = ((JsonElement) value).getAsJsonArray();
			SubjectMapper subjectMapper = new SubjectMapper();
			List<Subject> subjects = subjectMapper.createAndMapToSubjectList(involvedCADSubjects);
			model.setSubjects(subjects.stream().filter(subject -> subject.getPerson() != null).collect(Collectors.toList()));
		});
	}

	public EmergencyIncident createAndMapToEmergencyIncident(Set<Map.Entry<String, JsonElement>> data) {
		EmergencyIncident incident = new EmergencyIncident();
		return mapToEmergencyIncident(data, incident);
	}

	public EmergencyIncident mapToEmergencyIncident(Set<Map.Entry<String, JsonElement>> data, EmergencyIncident incident) {
		data.forEach(entry -> {
			Setter<EmergencyIncident> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(incident, entry.getValue());
			}
		});
		return incident;
	}

}
