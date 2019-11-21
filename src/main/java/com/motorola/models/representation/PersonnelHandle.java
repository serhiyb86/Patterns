package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class PersonnelHandle implements Serializable {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String alias;

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
		this.key = OneRmsHashUtils.convertCodeToOneRmsFormat(key);
	}

	/**
	 * Returns the value of property "agencyAlias".
	 * User's Agency Id
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

	/**
	 * Returns the value of property "alias".
	 * User Name
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Updates the value of property "alias".
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
