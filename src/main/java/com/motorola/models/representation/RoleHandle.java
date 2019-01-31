package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class RoleHandle implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String nameCode;

	private String agencyAlias;

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
	 * Role Name
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
	 * Returns the value of property "agencyAlias".
	 * Role's Agency Id
	 */
	public String getAgencyAlias() {
		return agencyAlias;
	}

	/**
	 * Updates the value of property "agencyAlias".
	 */
	public void setAgencyAlias(String agencyAlias) {
		this.agencyAlias = agencyAlias;
	}
}

