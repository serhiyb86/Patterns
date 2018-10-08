package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UpdateUnitStatusParameters implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private UnitHandle unitHandle;

	private String status;

	private String comment;

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
	 * Returns the value of property "status".
	 *
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Updates the value of property "status".
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Returns the value of property "comment".
	 *
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
