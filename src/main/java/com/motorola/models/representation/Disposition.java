package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.models.Config;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Disposition implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Lookup cadDisposition;

	private Lookup reportDisposition;

	private Lookup observedOffense;

	private UnitHandle unitHandle;

	private Date whenCadDispositionEntered;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenCadDispositionEntered() {
		return whenCadDispositionEntered;
	}

	/**
	 * Updates the value of property "whenCadDispositionEntered".
	 */
	public void setWhenCadDispositionEntered(Date whenCadDispositionEntered) {
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
