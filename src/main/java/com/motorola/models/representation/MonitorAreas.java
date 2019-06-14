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

	private List<String> areaKeys = new ArrayList<>();


	/**
	 * Returns the value of property "areaKeys".
	 * Areas Key [Mapped To Codes Table:-cad.jurisdiction.areas]
	 */
	public List<String> getAreaKeys() {
		return areaKeys;
	}

	/**
	 * Updates the value of property "areaKeys".
	 */
	public void setAreaKeys(List<String> areaKeys) {
		this.areaKeys = areaKeys;
	}



}
