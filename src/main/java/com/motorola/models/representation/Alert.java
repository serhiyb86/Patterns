package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.serializer.LocalDateSerializer;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Alert implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String typeKey;

	private String categoryKey;

	private String title;

	private String priority;

	private String comment;

	private String whenExpired;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate whenCreated;

	private Contact owner;

	private Contact contact;
	/**
	 * Returns the value of property "typeKey".
	 * Type of Alert (Key)[Mapped To Codes Table:-cad.alert.type]
	 */
	public String getTypeKey() {
		return typeKey;
	}

	/**
	 * Updates the value of property "typeKey".
	 */
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}

	/**
	 * Returns the value of property "categoryKey".
	 * Alert Category (Key) [Mapped To Codes Table:-cad.alert.category]
	 */
	public String getCategoryKey() {
		return categoryKey;
	}

	/**
	 * Updates the value of property "categoryKey".
	 */
	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	/**
	 * Returns the value of property "title".
	 * Alert Description
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Updates the value of property "title".
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Returns the value of property "priority".
	 * Alert Level
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Updates the value of property "priority".
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Returns the value of property "comment".
	 * Alert Description
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Updates the value of property "comment".
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Returns the value of property "whenExpired".
	 * Date and Time when the alert was expired
	 */
	public String getWhenExpired() {
		return whenExpired;
	}

	/**
	 * Updates the value of property "whenExpired".
	 */
	public void setWhenExpired(String whenExpired) {
		this.whenExpired = whenExpired;
	}

	/**
	 * Returns the value of property "whenCreated".
	 * Date and Time when the alert was created
	 */
	public LocalDate getWhenCreated() {
		return whenCreated;
	}

	/**
	 * Updates the value of property "whenCreated".
	 */
	public void setWhenCreated(LocalDate whenCreated) {
		this.whenCreated = whenCreated;
	}

	/**
	 * Returns the value of property "owner".
	 *
	 */
	public Contact getOwner() {
		return owner;
	}

	/**
	 * Updates the value of property "owner".
	 */
	public void setOwner(Contact owner) {
		this.owner = owner;
	}

	/**
	 * Returns the value of property "contact".
	 *
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Updates the value of property "contact".
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
