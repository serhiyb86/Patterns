package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Location implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private VerifiedAddress address;

	private String type;

	/**
	 * Returns the value of property "address".
	 *
	 */
	public VerifiedAddress getAddress() {
		return address;
	}

	/**
	 * Updates the value of property "address".
	 */
	public void setAddress(VerifiedAddress address) {
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
