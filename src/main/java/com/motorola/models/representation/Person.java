package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.Config;
import com.motorola.models.serializer.LocalDateSerializer;

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

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateOfBirth;

	private String comment;

	private Lookup race;

	private Lookup gender;

	private Long age;

	private String height;

	private Long weight;

	private Lookup build;

	private Lookup hairColor;

	private Lookup eyeColor;

	private String driverLicenseNumber;

	private String driverLicenseState;

	private List<ANIALI> aniali = new ArrayList<ANIALI>();

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Updates the value of property "dateOfBirth".
	 */
	public void setDateOfBirth(LocalDate dateOfBirth) {
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
	 * Returns the value of property "race".
	 *
	 */
	public Lookup getRace() {
		return race;
	}

	/**
	 * Updates the value of property "race".
	 */
	public void setRace(Lookup race) {
		this.race = race;
	}

	/**
	 * Returns the value of property "gender".
	 *
	 */
	public Lookup getGender() {
		return gender;
	}

	/**
	 * Updates the value of property "gender".
	 */
	public void setGender(Lookup gender) {
		this.gender = gender;
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
	public String getHeight() {
		return height;
	}

	/**
	 * Updates the value of property "height".
	 */
	public void setHeight(String height) {
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
	 * Returns the value of property "build".
	 *
	 */
	public Lookup getBuild() {
		return build;
	}

	/**
	 * Updates the value of property "build".
	 */
	public void setBuild(Lookup build) {
		this.build = build;
	}

	/**
	 * Returns the value of property "hairColor".
	 *
	 */
	public Lookup getHairColor() {
		return hairColor;
	}

	/**
	 * Updates the value of property "hairColor".
	 */
	public void setHairColor(Lookup hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * Returns the value of property "eyeColor".
	 *
	 */
	public Lookup getEyeColor() {
		return eyeColor;
	}

	/**
	 * Updates the value of property "eyeColor".
	 */
	public void setEyeColor(Lookup eyeColor) {
		this.eyeColor = eyeColor;
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

}
