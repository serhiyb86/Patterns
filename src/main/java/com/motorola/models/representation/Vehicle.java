package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Vehicle implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String licensePlate;

	private String licenseState;

	private String licenseTypeKey;

	private String licenseExpirationDate;

	private Long year;

	private String makeKey;

	private String modelKey;

	private String primaryColorKey;

	private String secondaryColorKey;

	private String styleKey;

	private String vin;

	private String owner;

	private String comment;

	private String modelAlias;

	private String makeAlias;

	private String licenseStateKey;

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
	 * Returns the value of property "licenseTypeKey".
	 * License Plate Type Key [Mapped To Codes Table:-vehicle.type]
	 */
	public String getLicenseTypeKey() {
		return licenseTypeKey;
	}

	/**
	 * Updates the value of property "licenseTypeKey".
	 */
	public void setLicenseTypeKey(String licenseTypeKey) {
		this.licenseTypeKey = OneRmsHashUtils.convertCodeToOneRmsFormat(licenseTypeKey);
	}

	/**
	 * Returns the value of property "licenseExpirationDate".
	 * License Plate expiration date
	 */
	public String getLicenseExpirationDate() {
		return licenseExpirationDate;
	}

	/**
	 * Updates the value of property "licenseExpirationDate".
	 */
	public void setLicenseExpirationDate(String licenseExpirationDate) {
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
	 * Returns the value of property "makeKey".
	 * Vehicle Make Key [Mapped To Codes Table:-vehicle.make]
	 */
	public String getMakeKey() {
		return makeKey;
	}

	/**
	 * Updates the value of property "makeKey".
	 */
	public void setMakeKey(String makeKey) {
		this.makeKey = OneRmsHashUtils.convertCodeToOneRmsFormat(makeKey);
	}

	/**
	 * Returns the value of property "modelKey".
	 * Vehicle Model Key [Mapped To Codes Table:-vehicle.model]
	 */
	public String getModelKey() {
		return modelKey;
	}

	/**
	 * Updates the value of property "modelKey".
	 */
	public void setModelKey(String modelKey) {
		this.modelKey = modelKey;
	}

	/**
	 * Returns the value of property "primaryColorKey".
	 * Primary Color Key of Vehicle [Mapped To Codes Table:-vehicle.color]
	 */
	public String getPrimaryColorKey() {
		return primaryColorKey;
	}

	/**
	 * Updates the value of property "primaryColorKey".
	 */
	public void setPrimaryColorKey(String primaryColorKey) {
		this.primaryColorKey = OneRmsHashUtils.convertCodeToOneRmsFormat(primaryColorKey);
	}

	/**
	 * Returns the value of property "secondaryColorKey".
	 * Secondary Color Key of Vehicle [Mapped To Codes Table:-vehicle.color]
	 */
	public String getSecondaryColorKey() {
		return secondaryColorKey;
	}

	/**
	 * Updates the value of property "secondaryColorKey".
	 */
	public void setSecondaryColorKey(String secondaryColorKey) {
		this.secondaryColorKey = OneRmsHashUtils.convertCodeToOneRmsFormat(secondaryColorKey);
	}

	/**
	 * Returns the value of property "styleKey".
	 * Vehicle Style
	 */
	public String getStyleKey() {
		return styleKey;
	}

	/**
	 * Updates the value of property "styleKey".
	 */
	public void setStyleKey(String styleKey) {
		this.styleKey = OneRmsHashUtils.convertCodeToOneRmsFormat(styleKey);
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

	/**
	 * Returns the value of property "modelAlias".
	 */
	public String getModelAlias() {
		return modelAlias;
	}

	/**
	 * Updates the value of property "modelAlias".
	 */
	public void setModelAlias(String modelAlias) {
		this.modelAlias = modelAlias;
	}

	/**
	 * Returns the value of property "makeAlias".
	 */
	public String getMakeAlias() {
		return makeAlias;
	}

	/**
	 * Updates the value of property "makeAlias".
	 */
	public void setMakeAlias(String makeAlias) {
		this.makeAlias = makeAlias;
	}

	/**
	 * Returns the value of property "licenseStateKey".
	 * licenseStateKey of Vehicle [Mapped To Codes Table:-states]
	 */
	public String getLicenseStateKey() {
		return licenseStateKey;
	}

	/**
	 * Updates the value of property "licenseStateKey".
	 */
	public void setLicenseStateKey(String licenseStateKey) {
		this.licenseStateKey = OneRmsHashUtils.convertCodeToOneRmsFormat(licenseStateKey);
	}
}
