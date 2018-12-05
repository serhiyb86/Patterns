package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user session - stored in Cosmos DB
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserSession implements java.io.Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String sessionId;

	private String userId;

	private String deviceId;

	private String roleKey;

	private List<String> apiAccessList = new ArrayList<String>();

	private MonitorAreas monitorAreas;

	private AdditionalInfo additionalInfo;

	private String whenSessionCreated;

	private String whenSessionUpdated;

	/**
	 * Returns the value of property "customerId".
	 *
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * Updates the value of property "customerId".
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * Returns the value of property "sessionId".
	 *
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Updates the value of property "sessionId".
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

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
	 * Returns the value of property "apiAccessList".
	 * List of security scopes defined in SecurityDefinitions in Cloud Client API to indicate the access rights to these API calls.
	 */
	public List<String> getApiAccessList() {
		return apiAccessList;
	}

	/**
	 * Updates the value of property "apiAccessList".
	 */
	public void setApiAccessList(List<String> apiAccessList) {
		this.apiAccessList = apiAccessList;
	}

	/**
	 * Returns the value of property "monitorAreas".
	 *
	 */
	public MonitorAreas getMonitorAreas() {
		return monitorAreas;
	}

	/**
	 * Updates the value of property "monitorAreas".
	 */
	public void setMonitorAreas(MonitorAreas monitorAreas) {
		this.monitorAreas = monitorAreas;
	}

	/**
	 * Returns the value of property "additionalInfo".
	 *
	 */
	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}

	/**
	 * Updates the value of property "additionalInfo".
	 */
	public void setAdditionalInfo(AdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	/**
	 * Returns the value of property "whenSessionCreated".
	 * The time stamp when the book on request is processed by on prem CAD
	 */
	public String getWhenSessionCreated() {
		return whenSessionCreated;
	}

	/**
	 * Updates the value of property "whenSessionCreated".
	 */
	public void setWhenSessionCreated(String whenSessionCreated) {
		this.whenSessionCreated = whenSessionCreated;
	}

	/**
	 * Returns the value of property "whenSessionUpdated".
	 * The time stamp when the book on request is processed by on prem CAD
	 */
	public String getWhenSessionUpdated() {
		return whenSessionUpdated;
	}

	/**
	 * Updates the value of property "whenSessionUpdated".
	 */
	public void setWhenSessionUpdated(String whenSessionUpdated) {
		this.whenSessionUpdated = whenSessionUpdated;
	}

}
