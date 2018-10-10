package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ApiResponse implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String correlationId;

	/**
	 * Returns the value of property "correlationId".
	 *
	 */
	public String getCorrelationId() {
		return correlationId;
	}

	/**
	 * Updates the value of property "correlationId".
	 */
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}

}
