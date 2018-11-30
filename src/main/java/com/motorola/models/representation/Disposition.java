/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Disposition implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup cadDisposition;

	private Lookup reportDisposition;

	private Lookup observedOffense;

	private UnitHandle unitHandle;

	private String whenCadDispositionEntered;

	private String comment;

	/**
	 * Returns the value of property "cadDisposition".
	 *
	 */
	public Lookup getCadDisposition() {
		return cadDisposition;
	}

	/**
	 * Updates the value of property "cadDisposition".
	 */
	public void setCadDisposition(Lookup cadDisposition) {
		this.cadDisposition = cadDisposition;
	}

	/**
	 * Returns the value of property "reportDisposition".
	 *
	 */
	public Lookup getReportDisposition() {
		return reportDisposition;
	}

	/**
	 * Updates the value of property "reportDisposition".
	 */
	public void setReportDisposition(Lookup reportDisposition) {
		this.reportDisposition = reportDisposition;
	}

	/**
	 * Returns the value of property "observedOffense".
	 *
	 */
	public Lookup getObservedOffense() {
		return observedOffense;
	}

	/**
	 * Updates the value of property "observedOffense".
	 */
	public void setObservedOffense(Lookup observedOffense) {
		this.observedOffense = observedOffense;
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
	public String getWhenCadDispositionEntered() {
		return whenCadDispositionEntered;
	}

	/**
	 * Updates the value of property "whenCadDispositionEntered".
	 */
	public void setWhenCadDispositionEntered(String whenCadDispositionEntered) {
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
