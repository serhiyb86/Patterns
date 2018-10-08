package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ApiError implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String domain;

	private String reason;

	private String message;

	private String extendedHelp;

	private String fieldPath;

	private List<KeyValue> parsedResources = new ArrayList<KeyValue>();

	/**
	 * Returns the value of property "errorCode".
	 * Different Error Conditions
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Updates the value of property "errorCode".
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Returns the value of property "domain".
	 *
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Updates the value of property "domain".
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Returns the value of property "reason".
	 *
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * Updates the value of property "reason".
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * Returns the value of property "message".
	 *
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Updates the value of property "message".
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Returns the value of property "extendedHelp".
	 *
	 */
	public String getExtendedHelp() {
		return extendedHelp;
	}

	/**
	 * Updates the value of property "extendedHelp".
	 */
	public void setExtendedHelp(String extendedHelp) {
		this.extendedHelp = extendedHelp;
	}

	/**
	 * Returns the value of property "fieldPath".
	 *
	 */
	public String getFieldPath() {
		return fieldPath;
	}

	/**
	 * Updates the value of property "fieldPath".
	 */
	public void setFieldPath(String fieldPath) {
		this.fieldPath = fieldPath;
	}

	/**
	 * Returns the value of property "parsedResources".
	 * Provide key value pair for the additional information such as unit ID. User ID, etc. - Will work with Nathan to further refine after he is available. The goal is to avoid the free form key value structure.
	 */
	public List<KeyValue> getParsedResources() {
		return parsedResources;
	}

	/**
	 * Updates the value of property "parsedResources".
	 */
	public void setParsedResources(List<KeyValue> parsedResources) {
		this.parsedResources = parsedResources;
	}

}
