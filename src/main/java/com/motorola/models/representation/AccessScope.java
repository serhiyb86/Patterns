package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines access scope for a specific agency
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class AccessScope implements Serializable {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String agencyKey;

	private List<String> apiAccessList = new ArrayList<>();

	/**
	 * Returns the value of property "agencyKey".
	 * Agency Key [Mapped To Codes Table:-cad.agencies]
	 */
	public String getAgencyKey() {
		return agencyKey;
	}

	/**
	 * Updates the value of property "agencyKey".
	 */
	public void setAgencyKey(String agencyKey) {
		this.agencyKey = agencyKey;
	}

	/**
	 * Returns the value of property "apiAccessList".
	 * List of security scopes defined in SecurityDefinitions in Cloud Client API to indicate the access rights to these API calls.
	 */
	public List<String> getApiAccessList() {
		return apiAccessList;
	}

	/**
	 * Updates the value of property "apiAccessList".
	 */
	public void setApiAccessList(List<String> apiAccessList) {
		this.apiAccessList = apiAccessList;
	}

}
