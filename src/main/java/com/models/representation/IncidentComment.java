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
public class IncidentComment implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String comments;

	private Date whenEntered;

	private Lookup source;

	private String audience;

	private UnitHandle onBehalfOfUnit;

	private PersonnelHandle onBehalfOfUser;

	private PersonnelHandle enteredBy;

	private DeviceHandle device;

	private String isPriority;

	/**
	 * Returns the value of property "comments".
	 * Comment text
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Updates the value of property "comments".
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Returns the value of property "whenEntered".
	 * Date and Time of the Incident Comment
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenEntered() {
		return whenEntered;
	}

	/**
	 * Updates the value of property "whenEntered".
	 */
	public void setWhenEntered(Date whenEntered) {
		this.whenEntered = whenEntered;
	}

	/**
	 * Returns the value of property "source".
	 *
	 */
	public Lookup getSource() {
		return source;
	}

	/**
	 * Updates the value of property "source".
	 */
	public void setSource(Lookup source) {
		this.source = source;
	}

	/**
	 * Returns the value of property "audience".
	 * Indicates the application where the comment is intended to show
	 */
	public String getAudience() {
		return audience;
	}

	/**
	 * Updates the value of property "audience".
	 */
	public void setAudience(String audience) {
		this.audience = audience;
	}

	/**
	 * Returns the value of property "onBehalfOfUnit".
	 *
	 */
	public UnitHandle getOnBehalfOfUnit() {
		return onBehalfOfUnit;
	}

	/**
	 * Updates the value of property "onBehalfOfUnit".
	 */
	public void setOnBehalfOfUnit(UnitHandle onBehalfOfUnit) {
		this.onBehalfOfUnit = onBehalfOfUnit;
	}

	/**
	 * Returns the value of property "onBehalfOfUser".
	 *
	 */
	public PersonnelHandle getOnBehalfOfUser() {
		return onBehalfOfUser;
	}

	/**
	 * Updates the value of property "onBehalfOfUser".
	 */
	public void setOnBehalfOfUser(PersonnelHandle onBehalfOfUser) {
		this.onBehalfOfUser = onBehalfOfUser;
	}

	/**
	 * Returns the value of property "enteredBy".
	 *
	 */
	public PersonnelHandle getEnteredBy() {
		return enteredBy;
	}

	/**
	 * Updates the value of property "enteredBy".
	 */
	public void setEnteredBy(PersonnelHandle enteredBy) {
		this.enteredBy = enteredBy;
	}

	/**
	 * Returns the value of property "device".
	 *
	 */
	public DeviceHandle getDevice() {
		return device;
	}

	/**
	 * Updates the value of property "device".
	 */
	public void setDevice(DeviceHandle device) {
		this.device = device;
	}

	/**
	 * Returns the value of property "isPriority".
	 * Indicates whether the comment is regular (false) or urgent (true)
	 */
	public String getIsPriority() {
		return isPriority;
	}

	/**
	 * Updates the value of property "isPriority".
	 */
	public void setIsPriority(String isPriority) {
		this.isPriority = isPriority;
	}

}
