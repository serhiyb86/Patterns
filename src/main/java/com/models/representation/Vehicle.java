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
public class Vehicle implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private String licenseState;

	private Lookup licenseType;

	private Date licenseExpirationDate;

	private Long year;

	private Lookup make;

	private Lookup model;

	private Lookup primaryColor;

	private Lookup secondaryColor;

	private Lookup style;

	private String vin;

	private String owner;

	private String comment;

	/**
	 * Returns the value of property "licensePlate".
	 * License Plate Number
	 */
	public String getLicensePlate() {
		return licensePlate;
	}

	/**
	 * Updates the value of property "licensePlate".
	 */
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	/**
	 * Returns the value of property "licenseState".
	 * License Plate State
	 */
	public String getLicenseState() {
		return licenseState;
	}

	/**
	 * Updates the value of property "licenseState".
	 */
	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	/**
	 * Returns the value of property "licenseType".
	 *
	 */
	public Lookup getLicenseType() {
		return licenseType;
	}

	/**
	 * Updates the value of property "licenseType".
	 */
	public void setLicenseType(Lookup licenseType) {
		this.licenseType = licenseType;
	}

	/**
	 * Returns the value of property "licenseExpirationDate".
	 * License Plate expiration date
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getLicenseExpirationDate() {
		return licenseExpirationDate;
	}

	/**
	 * Updates the value of property "licenseExpirationDate".
	 */
	public void setLicenseExpirationDate(Date licenseExpirationDate) {
		this.licenseExpirationDate = licenseExpirationDate;
	}

	/**
	 * Returns the value of property "year".
	 * Vehicle Year
	 */
	public Long getYear() {
		return year;
	}

	/**
	 * Updates the value of property "year".
	 */
	public void setYear(Long year) {
		this.year = year;
	}

	/**
	 * Returns the value of property "make".
	 *
	 */
	public Lookup getMake() {
		return make;
	}

	/**
	 * Updates the value of property "make".
	 */
	public void setMake(Lookup make) {
		this.make = make;
	}

	/**
	 * Returns the value of property "model".
	 *
	 */
	public Lookup getModel() {
		return model;
	}

	/**
	 * Updates the value of property "model".
	 */
	public void setModel(Lookup model) {
		this.model = model;
	}

	/**
	 * Returns the value of property "primaryColor".
	 *
	 */
	public Lookup getPrimaryColor() {
		return primaryColor;
	}

	/**
	 * Updates the value of property "primaryColor".
	 */
	public void setPrimaryColor(Lookup primaryColor) {
		this.primaryColor = primaryColor;
	}

	/**
	 * Returns the value of property "secondaryColor".
	 *
	 */
	public Lookup getSecondaryColor() {
		return secondaryColor;
	}

	/**
	 * Updates the value of property "secondaryColor".
	 */
	public void setSecondaryColor(Lookup secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	/**
	 * Returns the value of property "style".
	 *
	 */
	public Lookup getStyle() {
		return style;
	}

	/**
	 * Updates the value of property "style".
	 */
	public void setStyle(Lookup style) {
		this.style = style;
	}

	/**
	 * Returns the value of property "vin".
	 * Vehicle VIN
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * Updates the value of property "vin".
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * Returns the value of property "owner".
	 * Vehicle Owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Updates the value of property "owner".
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Returns the value of property "comment".
	 * Miscellaneous information about the Vehicle
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

}
