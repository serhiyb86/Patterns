package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Fleet implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String agencyKey;

	private String vehicleId;

	private List<Equipment> equipment = new ArrayList<Equipment>();

	private List<Capability> capabilities = new ArrayList<>();


	/**
	 * Returns the value of property "agencyKey".
	 * Agency Key [Mapped To Codes Table:-cad.agency]
	 */
	public String getAgencyKey() {
		return agencyKey;
	}

	/**
	 * Updates the value of property "agencyKey".
	 */
	public void setAgencyKey(String agencyKey) {
		this.agencyKey = agencyKey;
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
	 * Equipment assigned to the vehicle
	 */
	public List<Equipment> getEquipment() {
		return equipment;
	}

	/**
	 * Updates the value of property "equipment".
	 */
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	/**
	 * Returns the value of property "capabilities".
	 * Capabilities assigned to the Unit
	 */
	public List<Capability> getCapabilities() {
		return capabilities;
	}

	/**
	 * Updates the value of property "capabilities".
	 */
	public void setCapabilities(List<Capability> capabilities) {
		this.capabilities = capabilities;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = OneRmsHashUtils.convertCodeToOneRmsFormat(key);
	}
}
