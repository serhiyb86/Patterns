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

	private List<String> roleKeys = new ArrayList<>();

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
	 * Returns the value of property "roleKeys".
	 * Vehicle's Role(s) on the incident [Mapped To Codes Table:-vehicle.role]
	 */
	public List<String> getRoleKeys() {
		return roleKeys;
	}

	/**
	 * Updates the value of property "roleKeys".
	 */
	public void setRoleKeys(List<String> roleKeys) {
		this.roleKeys = roleKeys;
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
