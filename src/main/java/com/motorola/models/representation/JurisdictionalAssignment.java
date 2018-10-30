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

	private List<Lookup> areas = new ArrayList<>();

	private List<Lookup> sectors = new ArrayList<>();

	private List<Lookup> beats = new ArrayList<>();

	private Lookup homeStation;

	private Lookup assignedStation;

	/**
	 * Returns the value of property "areas".
	 * Areas
	 */
	public List<Lookup> getAreas() {
		return areas;
	}

	/**
	 * Updates the value of property "areas".
	 */
	public void setAreas(List<Lookup> areas) {
		this.areas = areas;
	}

	/**
	 * Returns the value of property "sectors".
	 * Sectors
	 */
	public List<Lookup> getSectors() {
		return sectors;
	}

	/**
	 * Updates the value of property "sectors".
	 */
	public void setSectors(List<Lookup> sectors) {
		this.sectors = sectors;
	}

	/**
	 * Returns the value of property "beats".
	 * Beats/zones
	 */
	public List<Lookup> getBeats() {
		return beats;
	}

	/**
	 * Updates the value of property "beats".
	 */
	public void setBeats(List<Lookup> beats) {
		this.beats = beats;
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
