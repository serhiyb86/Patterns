/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1.mappers.incident;

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mapper for converting Json Object with EmergencyIncident data to the {@link EmergencyIncident} object.
 */
public class EmergencyIncidentMapper {

	private static final Map<String, Setter<EmergencyIncident>> setters = new HashMap<>();
	private static final Map<Object, Setter<EmergencyIncident>> postSetters = new HashMap<>();

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
			//Copy this comments into all DispatchableIncidents after all fields mapped
			postSetters.put(incidentComments, (incident, comments) -> {
				List<DispatchableIncident> incidentDispatches = incident.getDispatches();
				if (incidentDispatches != null) {
					incidentDispatches.forEach(dispatch -> dispatch.setComments((List<IncidentComment>) comments));
				}
			});
		});

	}

	public EmergencyIncident createAndMapToEmergencyIncident(JsonObject data) {
		EmergencyIncident incident = new EmergencyIncident();
		EmergencyIncident emergencyIncident = mapToEmergencyIncident(data, incident);
		return executePostSetters(emergencyIncident);
	}

	private EmergencyIncident mapToEmergencyIncident(JsonObject data, EmergencyIncident incident) {
		data.entrySet().forEach(entry -> {
			Setter<EmergencyIncident> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(incident, entry.getValue());
			}
		});
		return incident;
	}

	/**
	 * Make additional changes to fields. Used for fields that can not be properly set during mapping.
	 */
	private EmergencyIncident executePostSetters(EmergencyIncident incident) {
		postSetters.forEach((data, setter) -> {
			setter.accept(incident, data);
		});
		return incident;
	}

}
