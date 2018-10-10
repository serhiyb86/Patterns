package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)

public class IncidentHandle implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String id;

	private String dispatchKey;

	private String dispatchAlias;

	/**
	 * Returns the value of property "key".
	 * Incident Key
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
	 * Returns the value of property "id".
	 * Incident Id
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

	/**
	 * Returns the value of property "dispatchKey".
	 * Dispatchable Incident Key
	 */
	public String getDispatchKey() {
		return dispatchKey;
	}

	/**
	 * Updates the value of property "dispatchKey".
	 */
	public void setDispatchKey(String dispatchKey) {
		this.dispatchKey = dispatchKey;
	}

	/**
	 * Returns the value of property "dispatchAlias".
	 * Dispatchable Incident Alias
	 */
	public String getDispatchAlias() {
		return dispatchAlias;
	}

	/**
	 * Updates the value of property "dispatchAlias".
	 */
	public void setDispatchAlias(String dispatchAlias) {
		this.dispatchAlias = dispatchAlias;
	}

}
