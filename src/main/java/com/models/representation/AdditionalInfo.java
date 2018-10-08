package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Additional information CAD book on needed to setup the CAD sign in proplery. Also used to return the actual CAD sign in properties if any modification is made by the backend server
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AdditionalInfo implements java.io.Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private UnitHandle unit;

	private List<Jurisdiction> jurisdictions = new ArrayList<Jurisdiction>();

	private Lookup district;

	private Lookup station;

	private String vehicleId;

	private List<Lookup> trustedAgencies = new ArrayList<com.models.representation.Lookup>();

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
	 * Returns the value of property "jurisdictions".
	 *
	 */
	public List<Jurisdiction> getJurisdictions() {
		return jurisdictions;
	}

	/**
	 * Updates the value of property "jurisdictions".
	 */
	public void setJurisdictions(List<Jurisdiction> jurisdictions) {
		this.jurisdictions = jurisdictions;
	}

	/**
	 * Returns the value of property "district".
	 *
	 */
	public Lookup getDistrict() {
		return district;
	}

	/**
	 * Updates the value of property "district".
	 */
	public void setDistrict(Lookup district) {
		this.district = district;
	}

	/**
	 * Returns the value of property "station".
	 *
	 */
	public Lookup getStation() {
		return station;
	}

	/**
	 * Updates the value of property "station".
	 */
	public void setStation(Lookup station) {
		this.station = station;
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
