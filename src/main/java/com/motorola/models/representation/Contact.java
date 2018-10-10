package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Contact implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String name;

	private String address;

	private List<String> phone = new ArrayList<String>();

	/**
	 * Returns the value of property "name".
	 * Person's full name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates the value of property "name".
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of property "address".
	 * Person's full address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Updates the value of property "address".
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns the value of property "phone".
	 * Phone Numbers
	 */
	public List<String> getPhone() {
		return phone;
	}

	/**
	 * Updates the value of property "phone".
	 */
	public void setPhone(List<String> phone) {
		this.phone = phone;
	}

}
