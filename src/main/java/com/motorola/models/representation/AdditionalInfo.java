package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Additional information CAD book on needed to setup the CAD sign in properly. Also used to return the actual CAD sign in properties if any modification is made by the backend server
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AdditionalInfo implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private UnitHandle unit;

	private List<JurisdictionalAssignment> jurisdictionalAssignments = new ArrayList<JurisdictionalAssignment>();

	private String vehicleId;

	private List<Lookup> trustedAgencies = new ArrayList<Lookup>();

	/**
	 * Returns the value of property "unit".
	 *
	 */
	public UnitHandle getUnit() {
		return unit;
	}

	/**
	 * Updates the value of property "unit".
	 */
	public void setUnit(UnitHandle unit) {
		this.unit = unit;
	}

	/**
	 * Returns the value of property "jurisdictionalAssignments".
	 *
	 */
	public List<JurisdictionalAssignment> getJurisdictionalAssignments() {
		return jurisdictionalAssignments;
	}

	/**
	 * Updates the value of property "jurisdictionalAssignments".
	 */
	public void setJurisdictionalAssignments(List<JurisdictionalAssignment> jurisdictionalAssignments) {
		this.jurisdictionalAssignments = jurisdictionalAssignments;
	}

	/**
	 * Returns the value of property "vehicleId".
	 *
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * Updates the value of property "vehicleId".
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * Returns the value of property "trustedAgencies".
	 * return trusted agency id list after log in - used by client to pick additional areas for coverage. No need to fill when submit the bookon.
	 */
	public List<Lookup> getTrustedAgencies() {
		return trustedAgencies;
	}

	/**
	 * Updates the value of property "trustedAgencies".
	 */
	public void setTrustedAgencies(List<Lookup> trustedAgencies) {
		this.trustedAgencies = trustedAgencies;
	}

}
