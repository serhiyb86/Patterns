package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class PersonnelHandle implements Serializable {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String alias;

	private String agencyAlias;

	private String cadUserKey;

	private String firstName;

	private String lastName;

	/**
	 * Returns the value of property "key".
	 * Unique Identifier
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Updates the value of property "key".
	 */
	public void setKey(String key) {
		this.key = OneRmsHashUtils.convertCodeToOneRmsFormat(key);
	}

	/**
	 * Returns the value of property "agencyAlias".
	 * User's Agency Id
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
	 * Returns the value of property "alias".
	 * User Name
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Updates the value of property "alias".
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getCadUserKey() {
		return cadUserKey;
	}

	public void setCadUserKey(String cadUserKey) {
		this.cadUserKey = OneRmsHashUtils.convertCodeToOneRmsFormat(cadUserKey);
	}

	/**
	 * Returns the value of property "firstName".
	 * User firstName
	 */
	public String getFirstName() { return firstName; }

	/**
	 * Updates the value of property "firstName".
	 */
	public void setFirstName(String firstName) { this.firstName = firstName; }

	/**
	 * Returns the value of property "lastName".
	 * User lastName
	 */
	public String getLastName() { return lastName; }

	/**
	 * Updates the value of property "lastName".
	 */
	public void setLastName(String lastName) { this.lastName = lastName; }
}
