/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.IncidentComment;
import com.motorola.models.representation.InvolvedVehicle;
import com.motorola.models.representation.Subject;
import com.motorola.translation.setter.Setter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mapper for converting Json Object with EmergencyIncident data to the {@link EmergencyIncident} object.
 */
public class EmergencyIncidentMapper {

	private static final Map<String, Setter<EmergencyIncident>> setters = new LinkedHashMap<>();

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
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISPATCHES, (model, value) -> {
			JsonArray dispatchesJson = ((JsonElement) value).getAsJsonArray();
			DispatchableIncidentMapper dispatchesMapper = new DispatchableIncidentMapper();
			List<DispatchableIncident> dispatches = dispatchesMapper.createAndMapToDispatchIncidentList(dispatchesJson);
			model.setDispatches(dispatches);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Vehicle.INVOLVED_VEHICLES, (model, value) -> {
			JsonArray involvedVehicleJSON = ((JsonElement) value).getAsJsonArray();
			InvolvedVehicleMapper vehicleMapper = new InvolvedVehicleMapper();
			List<InvolvedVehicle> involvedVehicles = vehicleMapper.createAndMapToInvolvedVehicleList(involvedVehicleJSON);
			model.setVehicles(involvedVehicles);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Comment.INCIDENT_COMMENTS, (model, value) -> {
			JsonArray incidentCommentsJSON = ((JsonElement) value).getAsJsonArray();
			IncidentCommentMapper incidentCommentMapper = new IncidentCommentMapper();
			List<IncidentComment> incidentComments = incidentCommentMapper.createAndMapToIncidentCommentList(incidentCommentsJSON);
			if (model.getDispatches() != null && model.getDispatches().size() > 0) {
				model.getDispatches().forEach(d -> d.setComments(incidentComments));
			}
		});

	}

	public EmergencyIncident createAndMapToEmergencyIncident(JsonObject data) {
		EmergencyIncident incident = new EmergencyIncident();
		EmergencyIncident emergencyIncident = mapToEmergencyIncident(data, incident);
		return emergencyIncident;
	}

	private EmergencyIncident mapToEmergencyIncident(JsonObject data, EmergencyIncident incident) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(incident, data.get(key));
			}
		});
		return incident;
	}

}
