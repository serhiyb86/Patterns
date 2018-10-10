package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Subject implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String contactRequested;

	private List<String> role = new ArrayList<>();

	private Person person;

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
	 * Returns the value of property "role".
	 * Subject's Role(s)
	 */
	public List<String> getRole() {
		return role;
	}

	/**
	 * Updates the value of property "role".
	 */
	public void setRole(List<String> role) {
		this.role = role;
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

}
