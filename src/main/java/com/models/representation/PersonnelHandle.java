package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class PersonnelHandle implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String nameCode;

	private Lookup agency;

	/**
	 * Returns the value of property "key".
	 * Unique Identifier
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

	/**
	 * Returns the value of property "nameCode".
	 * User Name
	 */
	public String getNameCode() {
		return nameCode;
	}

	/**
	 * Updates the value of property "nameCode".
	 */
	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

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

}
