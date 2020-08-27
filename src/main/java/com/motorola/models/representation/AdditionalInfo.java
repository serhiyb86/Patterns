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

	private UnitFeed unit;

	private JurisdictionalAssignment assignments;

	private String vehicleId;

    private List<String> trustedAgenciesKeys = new ArrayList<>();

	/**
	 * Returns the value of property "unit".
	 *
	 */
	public UnitFeed getUnit() {
		return unit;
	}

	/**
	 * Updates the value of property "unit".
	 */
	public void setUnit(UnitFeed unit) {
		this.unit = unit;
	}

	/**
	 * Returns the value of property "jurisdictionalAssignments".
	 *
	 */
	public JurisdictionalAssignment getAssignments() {
		return assignments;
	}

	/**
	 * Updates the value of property "jurisdictionalAssignments".
	 */
	public void setAssignments(JurisdictionalAssignment assignments) {
		this.assignments = assignments;
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
     * Returns the value of property "trustedAgenciesKeys". 
     * return trusted agency id list after log in - used by client to pick additional areas for coverage. No need to fill when submit the bookon.[Mapped To Codes Table:-cad.agency]
     */
    public List<String> getTrustedAgenciesKeys() {
        return trustedAgenciesKeys;
    }

    /**
     * Updates the value of property "trustedAgenciesKeys". 
     */
    public void setTrustedAgenciesKeys(List<String> trustedAgenciesKeys) {
        this.trustedAgenciesKeys = trustedAgenciesKeys;
    }

}
