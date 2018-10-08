package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Jurisdiction implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup area;

	private Lookup sector;

	private Lookup zone;

	/**
	 * Returns the value of property "area".
	 *
	 */
	public Lookup getArea() {
		return area;
	}

	/**
	 * Updates the value of property "area".
	 */
	public void setArea(Lookup area) {
		this.area = area;
	}

	/**
	 * Returns the value of property "sector".
	 *
	 */
	public Lookup getSector() {
		return sector;
	}

	/**
	 * Updates the value of property "sector".
	 */
	public void setSector(Lookup sector) {
		this.sector = sector;
	}

	/**
	 * Returns the value of property "zone".
	 *
	 */
	public Lookup getZone() {
		return zone;
	}

	/**
	 * Updates the value of property "zone".
	 */
	public void setZone(Lookup zone) {
		this.zone = zone;
	}

}
