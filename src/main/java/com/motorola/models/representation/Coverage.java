package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Coverage entry information returned by backend server as a result of CAD book on
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Coverage implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup agency;

	private List<Lookup> areas = new ArrayList<Lookup>();

	/**
	 * Returns the value of property "agency".
	 *
	 */
	public Lookup getAgency() {
		return agency;
	}

	/**
	 * Updates the value of property "agency".
	 */
	public void setAgency(Lookup agency) {
		this.agency = agency;
	}

	/**
	 * Returns the value of property "areas".
	 * Areas (P1) or Zones (Flex) covered by the session
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

}
