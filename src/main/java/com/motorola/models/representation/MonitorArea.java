package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Monitor area - support monitor subset of units in the station if specified
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class MonitorArea implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private List<Lookup> areas = new ArrayList<Lookup>();

	private List<StationUnits> stationUnits = new ArrayList<StationUnits>();

	/**
	 * Returns the value of property "areas".
	 *
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
