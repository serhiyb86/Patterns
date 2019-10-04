/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An object that contains the personnel info for an incident.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Personnel implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private PersonnelHandle personnelHandle;

	private List<Capability> capabilities = new ArrayList<Capability>();

	private List<Equipment> equipment = new ArrayList<Equipment>();

	/**
	 * Returns the value of property "personnelHandle".
	 *
	 */
	public PersonnelHandle getPersonnelHandle() {
		return personnelHandle;
	}

	/**
	 * Updates the value of property "personnelHandle".
	 */
	public void setPersonnelHandle(PersonnelHandle personnelHandle) {
		this.personnelHandle = personnelHandle;
	}

	/**
	 * Returns the value of property "capabilities".
	 * Capabilities info
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

	/**
	 * Returns the value of property "equipment".
	 * Equipments info
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

}
