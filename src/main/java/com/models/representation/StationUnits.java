package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Station key with optional units - if any units are specified, the scope is only monitor the units specified. Satisfy the requirement of only show specified Units within a fire station for status monitor
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class StationUnits implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup station;

	private List<UnitHandle> units = new ArrayList<UnitHandle>();

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
	 * Returns the value of property "units".
	 *
	 */
	public List<UnitHandle> getUnits() {
		return units;
	}

	/**
	 * Updates the value of property "units".
	 */
	public void setUnits(List<UnitHandle> units) {
		this.units = units;
	}

}
