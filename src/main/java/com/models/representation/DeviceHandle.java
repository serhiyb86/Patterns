package com.models.representation;

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

	private Lookup agency;

	private String id;

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

	/**
	 * Returns the value of property "id".
	 * Device Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Updates the value of property "id".
	 */
	public void setId(String id) {
		this.id = id;
	}

}
