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

	private String key;

	private List<String> role = new ArrayList<>();

	private Vehicle vehicle;

	/**
	 * Returns the value of property "key>.
	 * The unique identifier of an involved vehicle
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Updates the value of property "key"
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}

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
