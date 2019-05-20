/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.serializer.LocalDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Disposition implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String cadDispositionKey;

	private String reportDispositionKey;

	private String observedOffenseKey;

	private UnitHandle unitHandle;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime whenCadDispositionEntered;

	private String comment;

	/**
	 * Returns the value of property "key".
	 * The unique identifier of this disposition
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
	 * Returns the value of property "cadDispositionKey".
	 * CAD Disposition Key [Mapped To Codes Table:-cad.disposition.cadDisposition]
	 */
	public String getCadDispositionKey() {
		return cadDispositionKey;
	}

	/**
	 * Updates the value of property "cadDispositionKey".
	 */
	public void setCadDispositionKey(String cadDispositionKey) {
		this.cadDispositionKey = cadDispositionKey;
	}

	/**
	 * Returns the value of property "reportDispositionKey".
	 * Law Report Disposition Key [Mapped To Codes Table:-cad.disposition.reportDisposition]
	 */
	public String getReportDispositionKey() {
		return reportDispositionKey;
	}

	/**
	 * Updates the value of property "reportDispositionKey".
	 */
	public void setReportDispositionKey(String reportDispositionKey) {
		this.reportDispositionKey = reportDispositionKey;
	}

	/**
	 * Returns the value of property "observedOffenseKey".
	 * Observed Offense Key [Mapped To Codes Table:-cad.disposition.observedOffense]
	 */
	public String getObservedOffenseKey() {
		return observedOffenseKey;
	}

	/**
	 * Updates the value of property "observedOffenseKey".
	 */
	public void setObservedOffenseKey(String observedOffenseKey) {
		this.observedOffenseKey = observedOffenseKey;
	}

	/**
	 * Returns the value of property "unitHandle".
	 *
	 */
	public UnitHandle getUnitHandle() {
		return unitHandle;
	}

	/**
	 * Updates the value of property "unitHandle".
	 */
	public void setUnitHandle(UnitHandle unitHandle) {
		this.unitHandle = unitHandle;
	}

	/**
	 * Returns the value of property "whenCadDispositionEntered".
	 * Date and Time the Disposition was entered
	 */
	public LocalDateTime getWhenCadDispositionEntered() {
		return whenCadDispositionEntered;
	}

	/**
	 * Updates the value of property "whenCadDispositionEntered".
	 */
	public void setWhenCadDispositionEntered(LocalDateTime whenCadDispositionEntered) {
		this.whenCadDispositionEntered = whenCadDispositionEntered;
	}

	/**
	 * Returns the value of property "comment".
	 * Comment
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
