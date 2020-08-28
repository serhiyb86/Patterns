package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ReportNumber implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String alias;

	private UnitFeed unitFeed;

	private String agencyAlias;

	private String whenReportNumberInserted;

	/**
	 * Returns the value of property "key".
	 * Report Number Key
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
	 * Returns the value of property "alias".
	 * Report Number
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Updates the value of property "alias".
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * Returns the value of property "unitHandle".
	 *
	 */
	public UnitFeed getUnitFeed() {
		return unitFeed;
	}

	/**
	 * Updates the value of property "unitHandle".
	 */
	public void setUnitFeed(UnitFeed unitFeed) {
		this.unitFeed = unitFeed;
	}

	/**
	 * Returns the value of property "agencyAlias".
	 * Id of the agency that created the report
	 */
	public String getAgencyAlias() {
		return agencyAlias;
	}

	/**
	 * Updates the value of property "agencyAlias".
	 */
	public void setAgencyAlias(String agencyAlias) {
		this.agencyAlias = agencyAlias;
	}

	/**
	 * Returns the value of property "whenReportNumberInserted".
	 */
	public String getWhenReportNumberInserted() { return whenReportNumberInserted; }

	/**
	 * Updates the value of property "whenReportNumberInserted".
	 */
	public void setWhenReportNumberInserted(String whenReportNumberInserted) {
		this.whenReportNumberInserted = whenReportNumberInserted;
	}

}
