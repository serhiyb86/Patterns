package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class InvolvedVehicle implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private List<String> role = new ArrayList<String>();

	private Vehicle vehicle;

	/**
	 * Returns the value of property "role".
	 * Vehicle's Role(s) on the incident
	 */
	public List<String> getRole() {
		return role;
	}

	/**
	 * Updates the value of property "role".
	 */
	public void setRole(List<String> role) {
		this.role = role;
	}

	/**
	 * Returns the value of property "vehicle".
	 *
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Updates the value of property "vehicle".
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
