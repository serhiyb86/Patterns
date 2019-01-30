/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.Lookup;
import com.motorola.models.representation.Nature;
import com.motorola.models.representation.ReportNumber;
import com.motorola.models.representation.UnitHandle;
import com.motorola.translation.setter.custom.disposition.DispositionSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.AbstractMapper;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with DispatchesIncident data to the {@link DispatchableIncident} object.
 */
public class DispatchableIncidentMapper extends AbstractMapper {

	private static final Map<String, Setter<DispatchableIncident>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.ACTIVE_CALL_NUMBER, new StringSetter<>(DispatchableIncident::setAlias));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.ID, new StringSetter<>(DispatchableIncident::setKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISCIPLINE, (model, value) -> model.setDiscipline(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.AGENCY, (model, value) -> model.setAgency(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISCIPLINE_NATURE, (model, value) -> model.setNature(createNature((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.PRIORITY, new StringSetter<>(DispatchableIncident::setPriority));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.STATUS, (model, value) -> model.setStatus(createLookup((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.WHEN_STATUS_DECLARED, new StringSetter<>(DispatchableIncident::setWhenStatusDeclared));

		//Use custom setter for nested disposition model
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CLEARANCE, new DispositionSetter((disp, value) -> disp.setCadDisposition(createLookup((JsonElement) value))));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISPOSITION, new DispositionSetter((disp, value) -> disp.setReportDisposition(createLookup((JsonElement) value))));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.OBSERVED, new DispositionSetter((disp, value) -> disp.setObservedOffense(createLookup((JsonElement) value))));

		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DETERMINANT, (model, value) -> model.setProqaDeterminant(createProqaDeterminant((JsonElement) value)));
		// if incoming value is present and not empty - result=true
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.SCHEDULED_FOR , (model, value) -> model.setIsScheduled(checkSchedule((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RESPONSIBLE_UNIT_ID, (model, value) -> model.setPrimaryUnit(createUnit((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RELATED_RECORDS, (model, value) -> {
			ReportNumberMapper reportNumberMapper = new ReportNumberMapper();
			List<ReportNumber> reportNumberList = reportNumberMapper.createAndMapToReportNumberList(((JsonElement) value).getAsJsonArray());
			model.setReportNumbers(reportNumberList);
		});
	}

	private static Boolean checkSchedule(JsonElement value) {
		boolean result = false;
		if (value != null) {
			String scheduledFor = value.getAsString();
			result = !StringUtils.isEmpty(scheduledFor);
		}
		return result;
	}

	private static Lookup createProqaDeterminant(JsonElement value) {
		JsonElement determinantId = value.getAsJsonObject().get(InterfaceConstants.EmergencyIncident.Dispatches.ID);
		return createLookup(determinantId);
	}

	/**
	 * Creates {@link List <{@link DispatchableIncident}>} instance from the incoming {@link JsonArray} object.
	 *
	 * @param array {@link JsonArray} instance.
	 * @return {@link List<DispatchableIncident>} instance.
	 */
	public List<DispatchableIncident> createAndMapToDispatchIncidentList(JsonArray array) {
		List<DispatchableIncident> dispatchableIncidents = new ArrayList<>();
		for (JsonElement element : array) {
			JsonObject jsonObject = element.getAsJsonObject();
			dispatchableIncidents.add(mapToDispatchIncident(jsonObject));
		}
		return dispatchableIncidents;
	}

	/**
	 *  Fills the {@link DispatchableIncident} object
	 * @param data json data
	 * @return the {@link DispatchableIncident}object
	 */
	private DispatchableIncident mapToDispatchIncident(JsonObject data) {
		DispatchableIncident dispatchableIncident = new DispatchableIncident();
		// set default value for IsScheduled
		dispatchableIncident.setIsScheduled(false);
		data.entrySet().forEach(entry -> {
			Setter<DispatchableIncident> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(dispatchableIncident, entry.getValue());
			}
		});
		return dispatchableIncident;
	}

	/**
	 * Creates the {@link UnitHandle}
	 * @param value json value
	 * @return unit handler object
	 */
	private static UnitHandle createUnit(JsonElement value) {
		UnitHandle unitHandle = new UnitHandle();
		if (value != null) {
			unitHandle.setKey(value.getAsString());
		}
		return unitHandle;
	}

	/**
	 * Creates the {@link Nature} object.
	 * @param value json value
	 * @return nature object
	 */
	private static Nature createNature(JsonElement value) {
		Nature nature = new Nature();
		if (value != null) {
			nature.setNature(createLookup(value));
		}
		return nature;
	}

	public Lookup mapIncidentSource(JsonObject data) {
		String strVal = CadCloudUtils.getStringByKey(data,InterfaceConstants.EmergencyIncident.Dispatches.TYPE);
		if (strVal != null) {
			return createLookup(strVal);
		}
		return new Lookup();
	}
}
