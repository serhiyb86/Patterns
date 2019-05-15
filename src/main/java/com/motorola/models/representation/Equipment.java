package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Equipment implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String agencyKey;

	private String typeKey;

	private String equipmentItemName;

	private String key;

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
	 * Returns the value of property "typeKey".
	 * Equipment type key [Mapped To Codes Table:-cad.equipment.type]
	 */
	public String getTypeKey() {
		return typeKey;
	}

	/**
	 * Updates the value of property "typeKey".
	 */
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	/**
	 * Returns the value of property "equipmentItemName".
	 * Equipment item name
	 */
	public String getEquipmentItemName() {
		return equipmentItemName;
	}

	/**
	 * Updates the value of property "equipmentItemName".
	 */
	public void setEquipmentItemName(String equipmentItemName) {
		this.equipmentItemName = equipmentItemName;
	}

	/**
	 * Returns the value of property "key".
	 * Equipment Key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Updates the value of property "key".
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
