/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.IncidentComment;
import com.motorola.models.representation.PersonnelHandle;
import com.motorola.models.representation.UnitFeed;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.setter.ZonedDateTimeSetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Mapper for converting Json object with IncidentComment data to the {@link IncidentComment} object.
 */
public class IncidentCommentMapper {

	private static final Map<String, Setter<IncidentComment>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Comment.ID, new StringSetter<>(IncidentComment::setKey));
		setters.put(InterfaceConstants.EmergencyIncident.Comment.COMMENT, new StringSetter<>(IncidentComment::setComments));
		setters.put(InterfaceConstants.EmergencyIncident.Comment.WHEN_ENTERED,
			new ZonedDateTimeSetter<>(IncidentComment::setWhenEntered, InterfaceConstants.GeneralProperties.ZONED_DATE_TIME_FORMAT));
		setters.put(InterfaceConstants.EmergencyIncident.Comment.SOURCE, new StringSetter<>(IncidentComment::setSourceKey));
		setters.put(InterfaceConstants.EmergencyIncident.Comment.AUDIENCE, new StringSetter<>(IncidentComment::setAudience));
		setters.put(InterfaceConstants.EmergencyIncident.Comment.ON_BEHALF_OF_UNIT, (model, value) -> {
			JsonObject jsonObject = ((JsonElement) value).getAsJsonObject();
			JsonElement unitNumber = jsonObject.get(InterfaceConstants.EmergencyIncident.Comment.ON_BEHALF_OF_UNIT_ID);
			UnitFeed unit = new UnitFeed();
			unit.setKey(unitNumber.getAsString());
			model.setOnBehalfOfUnit(unit);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Comment.ON_BEHALF_OF_USER, (model, value) -> {
			JsonObject jsonObject = ((JsonElement) value).getAsJsonObject();
			JsonElement userName = jsonObject.get(InterfaceConstants.EmergencyIncident.Comment.ON_BEHALF_OF_USER_SNAME);
			PersonnelHandle user = new PersonnelHandle();
			user.setAlias(userName.getAsString());
			model.setOnBehalfOfUser(user);
		});
		setters.put(InterfaceConstants.EmergencyIncident.Comment.EnteredBy.ENTERED_BY, (model, value) ->
			model.setEnteredBy(new PersonnelHandleMapper().createAndMapPersonnelHandle((JsonObject) value))
		);
		//Ignore Device for now
		setters.put(InterfaceConstants.EmergencyIncident.Comment.URGENCY, (model, value) -> model.setIsPriority(parseUrgency((JsonElement) value)));
	}

	private static boolean parseUrgency(JsonElement value) {
		boolean parsedValue = false;
		if (value != null && value.getAsString().toLowerCase().equals("critical")) {
			parsedValue = true;
		}
		return parsedValue;
	}

	/**
	 * Creates {@link List <{@link IncidentComment}>} instance from the incoming {@link JsonArray} object.
	 *
	 * @param array {@link JsonArray} instance
	 * @return {@link List<IncidentComment>} instance
	 */
	public List<IncidentComment> createAndMapToIncidentCommentList(JsonArray array) {
		List<IncidentComment> incidentComments = new ArrayList<>();
		array.forEach(jsonElement -> {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			incidentComments.add(mapToIncidentComment(jsonObject.entrySet()));
		});
		return incidentComments;
	}

	/**
	 * Maps Json data into single {@link IncidentComment} object.
	 *
	 * @param data json data
	 * @return {@link IncidentComment} object
	 */
	private IncidentComment mapToIncidentComment(Set<Map.Entry<String, JsonElement>> data) {
		IncidentComment incidentComment = new IncidentComment();
		data.forEach(entry -> {
			Setter<IncidentComment> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(incidentComment, entry.getValue());
			}
		});
		return incidentComment;
	}

}
