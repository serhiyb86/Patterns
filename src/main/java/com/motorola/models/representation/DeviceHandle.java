package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DeviceHandle implements Serializable {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String agencyAlias;

	private String name;

	/**
	 * Returns the value of property "key".
	 * Device Key
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
	 * Returns the value of property "agencyAlias".
	 * Device Agency Id
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
	 * Returns the value of property "name".
	 * Device Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates the value of property "name".
	 */
	public void setName(String name) {
		this.name = name;
	}

}
