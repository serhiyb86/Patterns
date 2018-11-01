package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Monitor areas - support monitor subset of units in the station if specified
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class MonitorAreas implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private List<Lookup> areaKeys = new ArrayList<>();

	private List<StationUnits> stationUnits = new ArrayList<>();

	/**
	 * Returns the value of property "areaKeys".
	 *
	 */
	public List<Lookup> getAreaKeys() {
		return areaKeys;
	}

	/**
	 * Updates the value of property "areaKeys".
	 */
	public void setAreaKeys(List<Lookup> areaKeys) {
		this.areaKeys = areaKeys;
	}

	/**
	 * Returns the value of property "stationUnits".
	 *
	 */
	public List<StationUnits> getStationUnits() {
		return stationUnits;
	}

	/**
	 * Updates the value of property "stationUnits".
	 */
	public void setStationUnits(List<StationUnits> stationUnits) {
		this.stationUnits = stationUnits;
	}

}
