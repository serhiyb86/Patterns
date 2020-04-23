/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.CALL_COMMENT_PREFIX;
import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.DELIMITER;
import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.IDENTIFIER_INVOLVED_VEHICLE;
import static com.motorola.constants.InterfaceConstants.AuxiliaryDataMarkers.IDENTIFIER_SUBJECT;
import static com.motorola.constants.InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.ADDRESS;
import static com.motorola.constants.InterfaceConstants.EmergencyIncident.Dispatches.IncidentLocation.Address.ALERTS;
import static com.motorola.constants.InterfaceConstants.EmergencyIncident.GeneralProperties.REPORTED_EMERGENCY_LOCATION_KEY;
import static org.apache.commons.lang3.StringUtils.EMPTY;

/**
 * Mapper for converting Json Object with EmergencyIncident data to the {@link EmergencyIncident} object.
 */
public class EmergencyIncidentMapper extends GenericMapper<EmergencyIncident> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmergencyIncidentMapper.class);
	private static final Map<String, Setter<EmergencyIncident>> setters = new LinkedHashMap<>();
	private static DispatchableIncidentMapper dispatchesMapper = new DispatchableIncidentMapper();
	private static Map<String, Address> emergencyAlertLocationAddress = new HashMap<>();
	private static final String COMMENT_SUBJECT_PREFIX = CALL_COMMENT_PREFIX + DELIMITER + IDENTIFIER_SUBJECT + DELIMITER;
	private static final String COMMENT_INVOLVED_VEHICLE_PREFIX = CALL_COMMENT_PREFIX + DELIMITER + IDENTIFIER_INVOLVED_VEHICLE + DELIMITER;
	private static final String JSON_PAYLOAD_BEGINNING = "{";
	private static final boolean INTERNAL_COMMENTS_KEY = true;
	private static final boolean NOT_INTERNAL_COMMENTS_KEY = false;
	private static final JsonParser JSON_PARSER = new JsonParser();
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	static {
		//map address with alerts
		setters.put(REPORTED_EMERGENCY_LOCATION_KEY, (model, value) -> {
			JsonObject addressObject = CadCloudUtils.getJsonByKey((JsonObject) value, ADDRESS);
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
			Map<Boolean, List<IncidentComment>> commentsMap = incidentComments.stream()
				.collect(Collectors.groupingBy(comment -> comment.getComments().startsWith(CALL_COMMENT_PREFIX)));

			if (commentsMap.get(NOT_INTERNAL_COMMENTS_KEY) != null
				&& model.getDispatches() != null
				&& !model.getDispatches().isEmpty()) {
				model.getDispatches().forEach(d -> d.setComments(commentsMap.get(NOT_INTERNAL_COMMENTS_KEY)));
			}

			if (commentsMap.get(INTERNAL_COMMENTS_KEY) != null) {
				Map<String, List<IncidentComment>> internalCommentsMap = commentsMap.get(INTERNAL_COMMENTS_KEY).stream()
					.collect(Collectors.groupingBy(incidentComment -> {
						String commentText = incidentComment.getComments();
						return commentText.substring(0, commentText.indexOf(JSON_PAYLOAD_BEGINNING));
					}));

				if (internalCommentsMap.get(COMMENT_SUBJECT_PREFIX) != null) {
					internalCommentsMap.get(COMMENT_SUBJECT_PREFIX).forEach(comment -> {
						String commentText = comment.getComments();
						String payload = commentText.replace(COMMENT_SUBJECT_PREFIX, EMPTY);
						try {
							Subject subject = OBJECT_MAPPER.readValue(payload, Subject.class);
							String key = comment.getKey();
							subject.setKey(key);
							model.getSubjects().add(subject);
						}
						catch (IOException e) {
							LOGGER.error("Could not parse Subject from call comment {}.", payload, e);
						}
					});
				}

				if (internalCommentsMap.get(COMMENT_INVOLVED_VEHICLE_PREFIX) != null) {
					internalCommentsMap.get(COMMENT_INVOLVED_VEHICLE_PREFIX).forEach(comment -> {
						String commentText = comment.getComments();
						String payload = commentText.replace(COMMENT_INVOLVED_VEHICLE_PREFIX, EMPTY);
						try {
							InvolvedVehicle involvedVehicle = OBJECT_MAPPER.readValue(payload, InvolvedVehicle.class);
							String key = comment.getKey();
							involvedVehicle.setKey(key);
							model.getVehicles().add(involvedVehicle);
						}
						catch (IOException e) {
							LOGGER.error("Could not parse InvolvedVehicle from call comment {}.", payload, e);
						}
					});
				}

			}
		});
		setters.put(InterfaceConstants.EmergencyIncident.CUSTOMER_ID, new StringSetter<>(EmergencyIncident::setCustomerId));
		setters.put(InterfaceConstants.EmergencyIncident.WHEN_CREATED, (model, value) -> {
			model.setWhenCreated(((JsonElement) value).getAsString());
			model.setWhenUpdated(((JsonElement) value).getAsString());
		});
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
		mapAlertCount(dispatchableIncidents, emergencyAlertLocationAddress, data);

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

	private void mapAlertCount(List<DispatchableIncident> dispatches, Map<String, Address> emergencyAlertLocationAddress,
							   JsonObject data) {
		for (DispatchableIncident dispatchableIncident : dispatches) {
			Address address = dispatchableIncident.getLocation().getAddress();
			if (address != null) {
				String addressId = address.getKey();
				// check if address is emergency address (id is one of the keys in map)
				if (emergencyAlertLocationAddress.containsKey(addressId)) {

					JsonArray alerts = data.getAsJsonObject(REPORTED_EMERGENCY_LOCATION_KEY)
							.getAsJsonObject(ADDRESS)
							.getAsJsonArray(ALERTS);
					if (emergencyAlertLocationAddress.get(addressId).getIsVerified() && alerts !=null) {
						dispatchableIncident.setAlertCount(alerts.size());
					} else {
						dispatchableIncident.setAlertCount(0);
					}
				} else {
					dispatchableIncident.setAlertCount(0);
				}
			}
		}
	}




}
