package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ANIALI implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private VerifiedAddress address;

	private String carrier;

	private Long uncertaintyRadius;

	private Long confidenceLevel;

	private String classOfService;

	private String esn;

	private String rawData;

	private String whenReceived;

	/**
	 * Returns the value of property "address".
	 *
	 */
	public VerifiedAddress getAddress() {
		return address;
	}

	/**
	 * Updates the value of property "address".
	 */
	public void setAddress(VerifiedAddress address) {
		this.address = address;
	}

	/**
	 * Returns the value of property "carrier".
	 * Carrier
	 */
	public String getCarrier() {
		return carrier;
	}

	/**
	 * Updates the value of property "carrier".
	 */
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	/**
	 * Returns the value of property "uncertaintyRadius".
	 * Uncertainty Radius
	 */
	public Long getUncertaintyRadius() {
		return uncertaintyRadius;
	}

	/**
	 * Updates the value of property "uncertaintyRadius".
	 */
	public void setUncertaintyRadius(Long uncertaintyRadius) {
		this.uncertaintyRadius = uncertaintyRadius;
	}

	/**
	 * Returns the value of property "confidenceLevel".
	 * confidence Level
	 */
	public Long getConfidenceLevel() {
		return confidenceLevel;
	}

	/**
	 * Updates the value of property "confidenceLevel".
	 */
	public void setConfidenceLevel(Long confidenceLevel) {
		this.confidenceLevel = confidenceLevel;
	}

	/**
	 * Returns the value of property "classOfService".
	 * Class Of Service
	 */
	public String getClassOfService() {
		return classOfService;
	}

	/**
	 * Updates the value of property "classOfService".
	 */
	public void setClassOfService(String classOfService) {
		this.classOfService = classOfService;
	}

	/**
	 * Returns the value of property "esn".
	 * ESN
	 */
	public String getEsn() {
		return esn;
	}

	/**
	 * Updates the value of property "esn".
	 */
	public void setEsn(String esn) {
		this.esn = esn;
	}

	/**
	 * Returns the value of property "rawData".
	 * Raw Data of ANIALI feed
	 */
	public String getRawData() {
		return rawData;
	}

	/**
	 * Updates the value of property "rawData".
	 */
	public void setRawData(String rawData) {
		this.rawData = rawData;
	}

	/**
	 * Returns the value of property "whenReceived".
	 * Date and Time when ANIALI Received
	 */
	public String getWhenReceived() {
		return whenReceived;
	}

	/**
	 * Updates the value of property "whenReceived".
	 */
	public void setWhenReceived(String whenReceived) {
		this.whenReceived = whenReceived;
	}

}
