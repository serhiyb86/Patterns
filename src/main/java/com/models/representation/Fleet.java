package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Fleet implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup agency;

	private String vehicleId;

	private Equipment equipment;

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
	 * Returns the value of property "vehicleId".
	 * Vehicle Id
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * Updates the value of property "vehicleId".
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	/**
	 * Returns the value of property "equipment".
	 *
	 */
	public Equipment getEquipment() {
		return equipment;
	}

	/**
	 * Updates the value of property "equipment".
	 */
	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

}
