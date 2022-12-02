package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Capability implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Long quantity;

	private String capabilityKey;

	/**
	 * Returns the value of property "quantity".
	 * Number of assigned capabilities
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * Updates the value of property "quantity".
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * Returns the value of property "capabilityKey".
	 * Capability (Key) [Mapped To Codes Table:-cad.unit.capability]
	 */
	public String getCapabilityKey() {
		return capabilityKey;
	}

	/**
	 * Updates the value of property "capabilityKey".
	 */
	public void setCapabilityKey(String capabilityKey) {
		this.capabilityKey = capabilityKey;
	}

}
