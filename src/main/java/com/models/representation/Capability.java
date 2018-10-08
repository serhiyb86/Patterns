package com.models.representation;

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

	private Lookup capability;

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
	 * Returns the value of property "capability".
	 *
	 */
	public Lookup getCapability() {
		return capability;
	}

	/**
	 * Updates the value of property "capability".
	 */
	public void setCapability(Lookup capability) {
		this.capability = capability;
	}

}
