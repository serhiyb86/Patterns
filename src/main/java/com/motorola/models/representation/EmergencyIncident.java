package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An object that contains all the incident data. Note: We have to update the required field for both base and all child level objects. IncidentComment and DispatchableIncident are incomplete.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class EmergencyIncident implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String key;

	private String alias;

	private List<Subject> subjects = new ArrayList<Subject>();

	private List<InvolvedVehicle> vehicles = new ArrayList<InvolvedVehicle>();

	private List<DispatchableIncident> dispatches = new ArrayList<DispatchableIncident>();

	/**
	 * Returns the value of property "customerId".
	 * Use for DB partition
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * Updates the value of property "customerId".
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Returns the value of property "key".
	 * Incident Key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Updates the value of property "key".
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Returns the value of property "alias".
	 * Incident Alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Updates the value of property "alias".
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Returns the value of property "subjects".
	 * List of subjects for an incident
	 */
	public List<Subject> getSubjects() {
		return subjects;
	}

	/**
	 * Updates the value of property "subjects".
	 */
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	/**
	 * Returns the value of property "vehicles".
	 * List of vehicles for an incident
	 */
	public List<InvolvedVehicle> getVehicles() {
		return vehicles;
	}

	/**
	 * Updates the value of property "vehicles".
	 */
	public void setVehicles(List<InvolvedVehicle> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * Returns the value of property "dispatches".
	 * List of dispatched units for an incident
	 */
	public List<DispatchableIncident> getDispatches() {
		return dispatches;
	}

	/**
	 * Updates the value of property "dispatches".
	 */
	public void setDispatches(List<DispatchableIncident> dispatches) {
		this.dispatches = dispatches;
	}

}
