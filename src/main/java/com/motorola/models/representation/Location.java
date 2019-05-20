package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Location implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private Address address;

	private String type;

	/**
	 * Returns the value of property "key".
	 * The unique identifier for the location
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
	 * Returns the value of property "address".
	 *
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Updates the value of property "address".
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Returns the value of property "type".
	 * Type of Address
	 */
	public String getType() {
		return type;
	}

	/**
	 * Updates the value of property "type".
	 */
	public void setType(String type) {
		this.type = type;
	}

}
