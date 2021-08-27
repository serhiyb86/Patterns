package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Subject implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String contactRequested;

	private Person person;

	private List<String> roleKey;

	/**
	 * Returns the value of property "key".
	 * The unique identifier for a Subject
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
	 * Returns the value of property "contactRequested".
	 * Indicates whether Subject may be contacted (yes/No/May)
	 */
	public String getContactRequested() {
		return contactRequested;
	}

	/**
	 * Updates the value of property "contactRequested".
	 */
	public void setContactRequested(String contactRequested) {
		this.contactRequested = contactRequested;
	}

	/**
	 * Returns the value of property "person".
	 *
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Updates the value of property "person".
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * Returns the value of property "roleKey".
	 */
	public List<String> getRoleKey() {
		return roleKey;
	}

	/**
	 * Updates the value of property "roleKey".
	 */
	public void setRoleKey(List<String> roleKey) {
		this.roleKey = roleKey;
	}
}
