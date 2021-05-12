package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.Config;
import com.motorola.models.serializer.LocalDateSerializer;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Person implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String firstName;

	private String middleName;

	private String lastName;

	private String suffix;

	private List<Location> address = new ArrayList<Location>();

	private String phone;

	private String dateOfBirth;

	private String comment;

	private String raceKey;

	private String genderKey;

	private Long age;

	private Long height;

	private Long weight;

	private String buildKey;

	private String hairColorKey;

	private String eyeColorKey;

	private String driverLicenseNumber;

	private String driverLicenseState;

	private List<ANIALI> aniali = new ArrayList<ANIALI>();

	private String driverLicenseStateKey;

	/**
	 * Returns the value of property "firstName".
	 * Person's First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Updates the value of property "firstName".
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns the value of property "middleName".
	 * Person's Middle Name
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Updates the value of property "middleName".
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Returns the value of property "lastName".
	 * Person's Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Updates the value of property "lastName".
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Returns the value of property "suffix".
	 * Person's Name Suffix
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * Updates the value of property "suffix".
	 */
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * Returns the value of property "address".
	 * Person's Address
	 */
	public List<Location> getAddress() {
		return address;
	}

	/**
	 * Updates the value of property "address".
	 */
	public void setAddress(List<Location> address) {
		this.address = address;
	}

	/**
	 * Returns the value of property "phone".
	 * Person's Phone Number
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Updates the value of property "phone".
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Returns the value of property "dateOfBirth".
	 * Person's Date of Birth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Updates the value of property "dateOfBirth".
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Returns the value of property "comment".
	 * Narrative Text
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
	 * Returns the value of property "raceKey".
	 * Person's Race (Key) [Mapped To Codes Table:-person.race]
	 */
	public String getRaceKey() {
		return raceKey;
	}

	/**
	 * Updates the value of property "raceKey".
	 */
	public void setRaceKey(String raceKey) {
		this.raceKey = OneRmsHashUtils.convertCodeToOneRmsFormat(raceKey);
	}

	/**
	 * Returns the value of property "genderKey".
	 * Person's Gender (Key) [Mapped To Codes Table:-person.gender]
	 */
	public String getGenderKey() {
		return genderKey;
	}

	/**
	 * Updates the value of property "genderKey".
	 */
	public void setGenderKey(String genderKey) {
		this.genderKey = OneRmsHashUtils.convertCodeToOneRmsFormat(genderKey);
	}

	/**
	 * Returns the value of property "age".
	 * Person's Age
	 */
	public Long getAge() {
		return age;
	}

	/**
	 * Updates the value of property "age".
	 */
	public void setAge(Long age) {
		this.age = age;
	}

	/**
	 * Returns the value of property "height".
	 * Person's Height (inches)
	 */
	public Long getHeight() {
		return height;
	}

	/**
	 * Updates the value of property "height".
	 */
	public void setHeight(Long height) {
		this.height = height;
	}

	/**
	 * Returns the value of property "weight".
	 * Person's Weight (pounds)
	 */
	public Long getWeight() {
		return weight;
	}

	/**
	 * Updates the value of property "weight".
	 */
	public void setWeight(Long weight) {
		this.weight = weight;
	}

	/**
	 * Returns the value of property "buildKey".
	 * Person's Build (Key) [Mapped To Codes Table:-person.build]
	 */
	public String getBuildKey() {
		return buildKey;
	}

	/**
	 * Updates the value of property "buildKey".
	 */
	public void setBuildKey(String buildKey) {
		this.buildKey = OneRmsHashUtils.convertCodeToOneRmsFormat(buildKey);
	}

	/**
	 * Returns the value of property "hairColorKey".
	 * Person's Hair Color (Key) [Mapped To Codes Table:-person.hairColor]
	 */
	public String getHairColorKey() {
		return hairColorKey;
	}

	/**
	 * Updates the value of property "hairColorKey".
	 */
	public void setHairColorKey(String hairColorKey) {
		this.hairColorKey = OneRmsHashUtils.convertCodeToOneRmsFormat(hairColorKey);
	}

	/**
	 * Returns the value of property "eyeColorKey".
	 * Person's Eye Color (Key) [Mapped To Codes Table:-person.eyeColor]
	 */
	public String getEyeColorKey() {
		return eyeColorKey;
	}

	/**
	 * Updates the value of property "eyeColorKey".
	 */
	public void setEyeColorKey(String eyeColorKey) {
		this.eyeColorKey = OneRmsHashUtils.convertCodeToOneRmsFormat(eyeColorKey);
	}

	/**
	 * Returns the value of property "driverLicenseNumber".
	 * Person's Driver License Number
	 */
	public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}

	/**
	 * Updates the value of property "driverLicenseNumber".
	 */
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

	/**
	 * Returns the value of property "driverLicenseState".
	 * Person's Driver License State
	 */
	public String getDriverLicenseState() {
		return driverLicenseState;
	}

	/**
	 * Updates the value of property "driverLicenseState".
	 */
	public void setDriverLicenseState(String driverLicenseState) {
		this.driverLicenseState = driverLicenseState;
	}

	/**
	 * Returns the value of property "aniali".
	 * Associated ANIALI Data
	 */
	public List<ANIALI> getAniali() {
		return aniali;
	}

	/**
	 * Updates the value of property "aniali".
	 */
	public void setAniali(List<ANIALI> aniali) {
		this.aniali = aniali;
	}

	public String getDriverLicenseStateKey() {
		return driverLicenseStateKey;
	}

	public void setDriverLicenseStateKey(String driverLicenseStateKey) {
		this.driverLicenseStateKey = OneRmsHashUtils.convertCodeToOneRmsFormat(driverLicenseStateKey);
	}
}
