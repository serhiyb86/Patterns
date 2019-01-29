package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.ReportNumber;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.*;

/**
 * Mapper for converting Json Object with ReportNumber data to the {@link ReportNumber} object.
 */
public class ReportNumberMapper {

	private static final Map<String, Setter<ReportNumber>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RelatedRecord.REPORT_NUMBER, new StringSetter<>(ReportNumber::setAlias));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RelatedRecord.ID, new StringSetter<>(ReportNumber::setKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RelatedRecord.AGENCY, new StringSetter<>(ReportNumber::setAgencyAlias));
	}

	/**
	 * Creates {@link List <{@link ReportNumber}>} instance from the incoming {@link JsonArray} object.
	 *
	 * @param array {@link JsonArray} instance
	 * @return {@link List<ReportNumber>} instance
	 */
	public List<ReportNumber> createAndMapToReportNumberList(JsonArray array) {
		List<ReportNumber> incidentComments = new ArrayList<>();
		array.forEach(jsonElement -> {
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			incidentComments.add(mapToReportNumber(jsonObject.entrySet()));
		});
		return incidentComments;

	}

	/**
	 * Maps Json data into single {@link ReportNumber} object.
	 *
	 * @param data json data
	 * @return {@link ReportNumber} object
	 */
	private ReportNumber mapToReportNumber(Set<Map.Entry<String, JsonElement>> data) {
		ReportNumber reportNumber = new ReportNumber();

		data.forEach(entry -> {
			Setter<ReportNumber> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(reportNumber, entry.getValue());
			}
		});
		return reportNumber;
	}

}
