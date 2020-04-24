package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Equipment implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String agencyAlias;

	private String typeAlias;

	private String equipmentAlias;

	private String key;

	/**
	 * Returns the value of property "agencyKey".
	 * Agency Key [Mapped To Codes Table:-cad.agency]
	 */
	public String getAgencyAlias() {
		return agencyAlias;
	}

	/**
	 * Updates the value of property "agencyKey".
	 */
	public void setAgencyAlias(String agencyAlias) {
		this.agencyAlias = agencyAlias;
	}

	/**
	 * Returns the value of property "typeKey".
	 * Equipment type key [Mapped To Codes Table:-cad.equipment.type]
	 */
	public String getTypeAlias() {
		return typeAlias;
	}

	/**
	 * Updates the value of property "typeKey".
	 */
	public void setTypeAlias(String typeAlias) {
		this.typeAlias = typeAlias;
	}

	/**
	 * Returns the value of property "equipmentItemName".
	 * Equipment item name
	 */
	public String getEquipmentAlias() {
		return equipmentAlias;
	}

	/**
	 * Updates the value of property "equipmentItemName".
	 */
	public void setEquipmentAlias(String equipmentAlias) {
		this.equipmentAlias = equipmentAlias;
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
