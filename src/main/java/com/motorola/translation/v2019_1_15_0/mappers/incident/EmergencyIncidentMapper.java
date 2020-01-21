/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.Address;
import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.IncidentComment;
import com.motorola.models.representation.InvolvedVehicle;
import com.motorola.models.representation.Subject;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;
import com.motorola.utils.CadCloudUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.CALL_COMMENT_PREFIX;
import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.DELIMITER;
import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.IDENTIFIER_SUBJECT;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Mapper for converting Json Object with EmergencyIncident data to the {@link EmergencyIncident} object.
 */
public class EmergencyIncidentMapper extends GenericMapper<EmergencyIncident> {

	private static final Map<String, Setter<EmergencyIncident>> setters = new LinkedHashMap<>();
	private static DispatchableIncidentMapper dispatchesMapper = new DispatchableIncidentMapper();
	private static Map<String, Address> emergencyAlertLocationAddress = new HashMap<>();
	private static final String COMMENT_SUBJECT_PREFIX = CALL_COMMENT_PREFIX + DELIMITER + IDENTIFIER_SUBJECT + DELIMITER;
	private static final JsonParser JSON_PARSER = new JsonParser();

	static {
		//map address with alerts
		setters.put(InterfaceConstants.EmergencyIncident.GeneralProperties.REPORTED_EMERGENCY_LOCATION_KEY, (model, value) -> {
			JsonObject addressObject = CadCloudUtils.getJsonByKey((JsonObject) value, InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ADDRESS);
			AddressMapper addressMapper = new AddressMapper();
			Address address = addressMapper.createAndMapToAddress(addressObject, null);
			emergencyAlertLocationAddress.put(address.getKey(), address);
		});

		setters.put(InterfaceConstants.EmergencyIncident.GeneralProperties.ID_JSON_KEY, (model, value) -> {
			String id = ((JsonElement) value).getAsString();
			model.setAlias(id);
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
			List<IncidentComment> comments = incidentComments.stream()
				.filter(comment -> !comment.getComments().startsWith(CALL_COMMENT_PREFIX))
				.collect(Collectors.toList());
			if (model.getDispatches() != null && !model.getDispatches().isEmpty()) {
				model.getDispatches().forEach(d -> d.setComments(comments));
			}

			List<IncidentComment> internalComments = incidentComments.stream()
				.filter(comment -> comment.getComments().startsWith(CALL_COMMENT_PREFIX))
				.collect(Collectors.toList());
			internalComments.forEach(comment -> {
				String commentText = comment.getComments();
				String payload = commentText.replace(COMMENT_SUBJECT_PREFIX, EMPTY);
				JsonObject subjectJson = JSON_PARSER.parse(payload).getAsJsonObject();
				SubjectMapper subjectMapper = new SubjectMapper();
				Subject subject = subjectMapper.createAndMapToSubject(subjectJson);
				model.getSubjects().add(subject);
			});
		});
		setters.put(InterfaceConstants.EmergencyIncident.CUSTOMER_ID, new StringSetter<>(EmergencyIncident::setCustomerId));
		setters.put(InterfaceConstants.EmergencyIncident.WHEN_CREATED, new StringSetter<>(EmergencyIncident::setWhenCreated));
	}

	public EmergencyIncidentMapper() {
		super(setters);
	}

	public EmergencyIncident createAndMapToEmergencyIncident(JsonObject data) {
		EmergencyIncident incident = new EmergencyIncident();
		EmergencyIncident emergencyIncident = mapToEmergencyIncident(data, incident);
		// add incidentSource for each dispatch incident
		List<DispatchableIncident> dispatchableIncidents = emergencyIncident.getDispatches();
		dispatchableIncidents.forEach(dispatchableIncident -> {
			if (dispatchableIncident != null) {
				dispatchableIncident.setIncidentSourceKey(dispatchesMapper.mapIncidentSource(data));
			}
		});
		//it can be used in the future during the implementation of PremiseHazards endpoint
		//append alerts to the dispatches addresses
		//appendAlerts(dispatchableIncidents, emergencyAlertLocationAddress);
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

	private void appendAlerts(List<DispatchableIncident> dispatches, Map<String, Address> emergencyAlertLocationAddress) {
		for (DispatchableIncident dispatchableIncident : dispatches) {
			Address address = dispatchableIncident.getLocation().getAddress();
			if (address != null) {
				String addressId = address.getKey();
				// check if address is emergency address (id is one of the keys in map)
				if (emergencyAlertLocationAddress.containsKey(addressId)) {
					//it can be used in the future during the implementation of PremiseHazards endpoint
					//List<Alert> emergencyAlerts = emergencyAlertLocationAddress.get(addressId).getAlerts();
					// append alerts
					//address.setAlerts(emergencyAlerts);
				}
			}
		}
	}

}
