package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Nature implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String natureKey;

	private String modifyingcircumstanceKey;

	private Long alarmlevel;

	/**
	 * Returns the value of property "natureKey".
	 * Incident Type Key [Mapped To Codes Table:-cad.dispatchIncident.nature]
	 */
	public String getNatureKey() {
		return natureKey;
	}

	/**
	 * Updates the value of property "natureKey".
	 */
	public void setNatureKey(String natureKey) {
		this.natureKey = OneRmsHashUtils.convertCodeToOneRmsFormat(natureKey);
	}

	/**
	 * Returns the value of property "modifyingcircumstanceKey".
	 * Modifying Circumstance Key [Mapped To Codes Table:-cad.dispatchIncident.nature.modifyingCircumstance]
	 */
	public String getModifyingcircumstanceKey() {
		return modifyingcircumstanceKey;
	}

	/**
	 * Updates the value of property "modifyingcircumstanceKey".
	 */
	public void setModifyingcircumstanceKey(String modifyingcircumstanceKey) {
		this.modifyingcircumstanceKey = modifyingcircumstanceKey;
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
