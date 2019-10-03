package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Address implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String fullText;

	private String crossStreet;

	private String building;

	private String floor;

	private String apartment;

	private String subdivision;

	private String city;

	private String state;

	private String zip;

	private String latitude;

	private String longitude;

	private String description;

	private Jurisdiction jurisdiction;

	private Boolean isVerified;

	private String geoverificationLevel;

	private String name;

	/**
	 * Returns the value of property "key".
	 * Unique identifier of the address, currently used for finding Dispatches with the same address
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
	 * Returns the value of property "fullText".
	 * Full Text of the Address
	 */
	public String getFullText() {
		return fullText;
	}

	/**
	 * Updates the value of property "fullText".
	 */
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	/**
	 * Returns the value of property "crossStreet".
	 * Cross Street
	 */
	public String getCrossStreet() {
		return crossStreet;
	}

	/**
	 * Updates the value of property "crossStreet".
	 */
	public void setCrossStreet(String crossStreet) {
		this.crossStreet = crossStreet;
	}

	/**
	 * Returns the value of property "building".
	 * Building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * Updates the value of property "building".
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * Returns the value of property "floor".
	 * Floor
	 */
	public String getFloor() {
		return floor;
	}

	/**
	 * Updates the value of property "floor".
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}

	/**
	 * Returns the value of property "apartment".
	 * Apartment
	 */
	public String getApartment() {
		return apartment;
	}

	/**
	 * Updates the value of property "apartment".
	 */
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	/**
	 * Returns the value of property "subdivision".
	 * Sub Division
	 */
	public String getSubdivision() {
		return subdivision;
	}

	/**
	 * Updates the value of property "subdivision".
	 */
	public void setSubdivision(String subdivision) {
		this.subdivision = subdivision;
	}

	/**
	 * Returns the value of property "city".
	 * City
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Updates the value of property "city".
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns the value of property "state".
	 * State
	 */
	public String getState() {
		return state;
	}

	/**
	 * Updates the value of property "state".
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the value of property "zip".
	 * Postal Code
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * Updates the value of property "zip".
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Returns the value of property "latitude".
	 * Degrees Latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * Updates the value of property "latitude".
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * Returns the value of property "longitude".
	 * Degrees Longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * Updates the value of property "longitude".
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * Returns the value of property "description".
	 * Description of the Address
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Updates the value of property "description".
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the value of property "jurisdiction".
	 *
	 */
	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	/**
	 * Updates the value of property "jurisdiction".
	 */
	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	/**
	 * Returns the value of property "isVerified".
	 * Indicates whether the Address is verified
	 */
	public Boolean getIsVerified() {
		return isVerified;
	}

	/**
	 * Updates the value of property "isVerified".
	 */
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}

	/**
	 * Returns the value of property "geoverificationLevel".
	 * Geoverification Level
	 */
	public String getGeoverificationLevel() {
		return geoverificationLevel;
	}

	/**
	 * Updates the value of property "geoverificationLevel".
	 */
	public void setGeoverificationLevel(String geoverificationLevel) {
		this.geoverificationLevel = geoverificationLevel;
	}

	/**
	 * Returns the value of property "name".
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

}
