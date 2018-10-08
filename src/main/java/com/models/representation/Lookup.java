package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Lookup implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String uid;

	/**
	 * Returns the value of property "uid".
	 * The unique identifier assigned to the customer interface.
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Updates the value of property "uid".
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

}
