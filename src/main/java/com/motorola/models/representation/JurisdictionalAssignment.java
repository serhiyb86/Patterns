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

	private List<String> areasKeys = new ArrayList<>();

	private List<String> sectorsKeys = new ArrayList<>();

	private List<String> beatsKeys = new ArrayList<>();

	private String homeStationKey;

	private String assignedStationKey;

	/**
	 * Returns the value of property "areasKeys".
	 * Areas [Mapped To Codes Table:-cad.jurisdiction.area]
	 */
	public List<String> getAreasKeys() {
		return areasKeys;
	}

	/**
	 * Updates the value of property "areasKeys".
	 */
	public void setAreasKeys(List<String> areasKeys) {
		this.areasKeys = areasKeys;
	}

	/**
	 * Returns the value of property "sectorsKeys".
	 * Sectors [Mapped To Codes Table:-cad.jurisdiction.sector]
	 */
	public List<String> getSectorsKeys() {
		return sectorsKeys;
	}


	/**
	 * Updates the value of property "sectorsKeys".
	 */
	public void setSectorsKeys(List<String> sectorsKeys) {
		this.sectorsKeys = sectorsKeys;
	}

	/**
	 * Returns the value of property "beatsKeys".
	 * Beats/zones [Mapped To Codes Table:-cad.jurisdiction.beat]
	 */
	public List<String> getBeatsKeys() {
		return beatsKeys;
	}

	/**
	 * Updates the value of property "beatsKeys".
	 */
	public void setBeatsKeys(List<String> beatsKeys) {
		this.beatsKeys = beatsKeys;
	}

	/**
	 * Returns the value of property "homeStationKey".
	 * Home (Default) Station Key [Mapped To Codes Table:-cad.jurisdiction.station]
	 */
	public String getHomeStationKey() {
		return homeStationKey;
	}

	/**
	 * Updates the value of property "homeStationKey".
	 */
	public void setHomeStationKey(String homeStationKey) {
		this.homeStationKey = homeStationKey;
	}

	/**
	 * Returns the value of property "assignedStationKey".
	 * Currently assigned Station Key [Mapped To Codes Table:-cad.jurisdiction.station]
	 */
	public String getAssignedStationKey() {
		return assignedStationKey;
	}

	/**
	 * Updates the value of property "assignedStationKey".
	 */
	public void setAssignedStationKey(String assignedStationKey) {
		this.assignedStationKey = assignedStationKey;
	}

}
