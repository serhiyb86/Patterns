package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class JurisdictionalAssignment implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private List<String> area = new ArrayList<String>();

	private List<String> sector = new ArrayList<java.lang.String>();

	private List<String> zone = new ArrayList<java.lang.String>();

	private Lookup homeStation;

	private Lookup assignedStation;

	/**
	 * Returns the value of property "area".
	 * Area
	 */
	public List<String> getArea() {
		return area;
	}

	/**
	 * Updates the value of property "area".
	 */
	public void setArea(List<String> area) {
		this.area = area;
	}

	/**
	 * Returns the value of property "sector".
	 * Sector
	 */
	public List<String> getSector() {
		return sector;
	}

	/**
	 * Updates the value of property "sector".
	 */
	public void setSector(List<String> sector) {
		this.sector = sector;
	}

	/**
	 * Returns the value of property "zone".
	 * Beat
	 */
	public List<String> getZone() {
		return zone;
	}

	/**
	 * Updates the value of property "zone".
	 */
	public void setZone(List<String> zone) {
		this.zone = zone;
	}

	/**
	 * Returns the value of property "homeStation".
	 *
	 */
	public Lookup getHomeStation() {
		return homeStation;
	}

	/**
	 * Updates the value of property "homeStation".
	 */
	public void setHomeStation(Lookup homeStation) {
		this.homeStation = homeStation;
	}

	/**
	 * Returns the value of property "assignedStation".
	 *
	 */
	public Lookup getAssignedStation() {
		return assignedStation;
	}

	/**
	 * Updates the value of property "assignedStation".
	 */
	public void setAssignedStation(Lookup assignedStation) {
		this.assignedStation = assignedStation;
	}

}
