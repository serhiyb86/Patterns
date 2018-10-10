package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Nature implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup nature;

	private Lookup modifyingcircumstance;

	private Long alarmlevel;

	/**
	 * Returns the value of property "nature".
	 *
	 */
	public Lookup getNature() {
		return nature;
	}

	/**
	 * Updates the value of property "nature".
	 */
	public void setNature(Lookup nature) {
		this.nature = nature;
	}

	/**
	 * Returns the value of property "modifyingcircumstance".
	 *
	 */
	public Lookup getModifyingcircumstance() {
		return modifyingcircumstance;
	}

	/**
	 * Updates the value of property "modifyingcircumstance".
	 */
	public void setModifyingcircumstance(Lookup modifyingcircumstance) {
		this.modifyingcircumstance = modifyingcircumstance;
	}

	/**
	 * Returns the value of property "alarmlevel".
	 * Alarm Level
	 */
	public Long getAlarmlevel() {
		return alarmlevel;
	}

	/**
	 * Updates the value of property "alarmlevel".
	 */
	public void setAlarmlevel(Long alarmlevel) {
		this.alarmlevel = alarmlevel;
	}

}
