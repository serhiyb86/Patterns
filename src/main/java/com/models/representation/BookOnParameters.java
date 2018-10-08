package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class BookOnParameters implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String userId;

	private String deviceId;

	private String roleKey;

	private List<AdditionalInfo> additionalInfo = new ArrayList<>();

	private Boolean isRemovePrevSession;

	private Boolean isUseUnitPreassignments;

	/**
	 * Returns the value of property "userId".
	 *
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Updates the value of property "userId".
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Returns the value of property "deviceId".
	 *
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * Updates the value of property "deviceId".
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * Returns the value of property "roleKey".
	 *
	 */
	public String getRoleKey() {
		return roleKey;
	}

	/**
	 * Updates the value of property "roleKey".
	 */
	public void setRoleKey(String roleKey) {
		this.roleKey = roleKey;
	}

	/**
	 * Returns the value of property "additionalInfo".
	 *
	 */
	public java.util.List<AdditionalInfo> getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Updates the value of property "additionalInfo".
	 */
	public void setAdditionalInfo(List<AdditionalInfo> additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	/**
	 * Returns the value of property "isRemovePrevSession".
	 *
	 */
	public Boolean getIsRemovePrevSession() {
		return isRemovePrevSession;
	}

	/**
	 * Updates the value of property "isRemovePrevSession".
	 */
	public void setIsRemovePrevSession(Boolean isRemovePrevSession) {
		this.isRemovePrevSession = isRemovePrevSession;
	}

	/**
	 * Returns the value of property "isUseUnitPreassignments".
	 *
	 */
	public Boolean getIsUseUnitPreassignments() {
		return isUseUnitPreassignments;
	}

	/**
	 * Updates the value of property "isUseUnitPreassignments".
	 */
	public void setIsUseUnitPreassignments(Boolean isUseUnitPreassignments) {
		this.isUseUnitPreassignments = isUseUnitPreassignments;
	}

}
