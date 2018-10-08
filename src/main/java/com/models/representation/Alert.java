package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.models.Config;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Alert implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup type;

	private Lookup category;

	private String title;

	private String priority;

	private String comment;

	private String whenExpired;

	private Date whenCreated;

	private Contact owner;

	private Contact contact;

	/**
	 * Returns the value of property "type".
	 *
	 */
	public Lookup getType() {
		return type;
	}

	/**
	 * Updates the value of property "type".
	 */
	public void setType(Lookup type) {
		this.type = type;
	}

	/**
	 * Returns the value of property "category".
	 *
	 */
	public Lookup getCategory() {
		return category;
	}

	/**
	 * Updates the value of property "category".
	 */
	public void setCategory(Lookup category) {
		this.category = category;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenCreated() {
		return whenCreated;
	}

	/**
	 * Updates the value of property "whenCreated".
	 */
	public void setWhenCreated(Date whenCreated) {
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
