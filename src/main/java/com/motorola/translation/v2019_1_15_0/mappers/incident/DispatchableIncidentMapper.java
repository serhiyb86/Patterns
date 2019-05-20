/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.incident;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.constants.InterfaceConstants.EmergencyIncident.Dispatches;
import com.motorola.models.representation.DispatchableIncident;
import com.motorola.models.representation.Disposition;
import com.motorola.models.representation.Jurisdiction;
import com.motorola.models.representation.Location;
import com.motorola.models.representation.Nature;
import com.motorola.models.representation.ReportNumber;
import com.motorola.models.representation.UnitHandle;
import com.motorola.translation.setter.custom.disposition.DispositionSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.utils.CadCloudUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper for converting Json Object with DispatchesIncident data to the {@link DispatchableIncident} object.
 */
public class DispatchableIncidentMapper {

	private static final Map<String, Setter<DispatchableIncident>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.ACTIVE_CALL_NUMBER, new StringSetter<>(DispatchableIncident::setAlias));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.ID, new StringSetter<>(DispatchableIncident::setKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISCIPLINE, new StringSetter<>(DispatchableIncident::setDisciplineKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.AGENCY, new StringSetter<>(DispatchableIncident::setAgencyKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISCIPLINE_NATURE, (model, value) -> model.setNature(createNature((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.PRIORITY, new StringSetter<>(DispatchableIncident::setPriority));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.STATUS, new StringSetter<>(DispatchableIncident::setStatusKey));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.WHEN_STATUS_DECLARED, new StringSetter<>(DispatchableIncident::setWhenStatusDeclared));

		//Use custom setter for nested disposition model
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.CLEARANCE, new DispositionSetter((disp, value) -> disp.setCadDispositionKey(extractValue(value))));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DISPOSITION, new DispositionSetter((disp, value) -> disp.setReportDispositionKey(extractValue(value))));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.OBSERVED, new DispositionSetter((disp, value) -> disp.setObservedOffenseKey(extractValue(value))));

		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.DETERMINANT, (model, value) -> model.setProqaDeterminantKey(createProqaDeterminant((JsonElement) value)));
		// if incoming value is present and not empty - result=true
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.SCHEDULED_FOR, (model, value) -> model.setIsScheduled(checkSchedule((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RESPONSIBLE_UNIT_ID, (model, value) -> model.setPrimaryUnit(createUnit((JsonElement) value)));
		setters.put(InterfaceConstants.EmergencyIncident.Dispatches.RELATED_RECORDS, (model, value) -> {
			ReportNumberMapper reportNumberMapper = new ReportNumberMapper();
			List<ReportNumber> reportNumberList = reportNumberMapper.createAndMapToReportNumberList(((JsonElement) value).getAsJsonArray());
			model.setReportNumbers(reportNumberList);
		});
		setters.put(Dispatches.RESPONDING_UNIT_IDS, (model, value) -> model.setAssignedUnits(createAndMapToUnitsList((JsonArray) value)));
	}

	private static String extractValue(Object value) {
		return CadCloudUtils.getStringFromJsonElement((JsonElement) value);
	}

	private static Boolean checkSchedule(JsonElement value) {
		boolean result = false;
		if (value != null) {
			String scheduledFor = value.getAsString();
			result = !StringUtils.isEmpty(scheduledFor);
		}
		return result;
	}

	private static String createProqaDeterminant(JsonElement value) {
		JsonElement determinantId = value.getAsJsonObject().get(InterfaceConstants.EmergencyIncident.Dispatches.ID);
		return determinantId != null ? determinantId.getAsString() : "";
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
		dispatchableIncident.setLocation(createLocation(data));
		data.entrySet().forEach(entry -> {
			Setter<DispatchableIncident> consumer = setters.get(entry.getKey());
			if (consumer != null) {
				consumer.accept(dispatchableIncident, entry.getValue());
			}
		});
		// added required field key for disposition
		// the index 0 is the same like in DispositionSetter implemented  for IF-2017
		List <Disposition> dispositions = dispatchableIncident.getDispositions();
		if(!dispositions.isEmpty()){
			dispositions.get(0).setKey("1");
		}
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
			nature.setNatureKey(value.getAsString());
		}
		return nature;
	}

	public String mapIncidentSource(JsonObject data) {
		return CadCloudUtils.getStringByKey(data, InterfaceConstants.EmergencyIncident.Dispatches.TYPE);
	}

	/**
	 * Creates {@link Jurisdiction} object
	 */
	private Jurisdiction createJurisdiction(String zone) {
		Jurisdiction jurisdiction = null;
		if (StringUtils.isNotBlank(zone)) {
			jurisdiction = new Jurisdiction();
			jurisdiction.setAreaKey(zone);
		}
		return jurisdiction;
	}

	/**
	 * Creates {@link Location} object
	 */
	private Location createLocation(JsonObject data) {
		JsonObject locationJson = CadCloudUtils.getJsonByKey(data, InterfaceConstants.EmergencyIncident.Dispatches.INCIDENT_LOCATION);
		String zone = CadCloudUtils.getStringByKey(data, InterfaceConstants.EmergencyIncident.Dispatches.ZONE);

		if (locationJson != null) {
			LocationMapper locationMapper = new LocationMapper();
			return locationMapper.createAndMapLocation(locationJson, createJurisdiction(zone));
		}
		else {
			return new Location();
		}
	}

	private static List<UnitHandle> createAndMapToUnitsList(JsonArray array) {
		List<UnitHandle> unitHandles = new ArrayList<>();
		for (JsonElement element : array) {
			unitHandles.add(createUnit(element));
		}
		return unitHandles;
	}
}
