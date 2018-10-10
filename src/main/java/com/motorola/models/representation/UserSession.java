package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.models.Config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a user session - stored in Cosmos DB
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserSession implements java.io.Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private java.lang.String sessionId;

	private java.lang.String userId;

	private java.lang.String deviceId;

	private java.lang.String roleKey;

	private List<String> apiAccessList = new ArrayList<String>();

	private List<Coverage> coverages = new ArrayList<Coverage>();

	private List<MonitorArea> monitorScope = new ArrayList<MonitorArea>();

	private AdditionalInfo additionalInfo;

	private Date whenSessionCreated;

	private Date whenSessionUpdated;

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
	 * Returns the value of property "coverages".
	 * List of coverage entries
	 */
	public List<Coverage> getCoverages() {
		return coverages;
	}

	/**
	 * Updates the value of property "coverages".
	 */
	public void setCoverages(List<Coverage> coverages) {
		this.coverages = coverages;
	}

	/**
	 * Returns the value of property "monitorScope".
	 * Store monitor areas or monitor station/units
	 */
	public List<MonitorArea> getMonitorScope() {
		return monitorScope;
	}

	/**
	 * Updates the value of property "monitorScope".
	 */
	public void setMonitorScope(List<MonitorArea> monitorScope) {
		this.monitorScope = monitorScope;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenSessionCreated() {
		return whenSessionCreated;
	}

	/**
	 * Updates the value of property "whenSessionCreated".
	 */
	public void setWhenSessionCreated(Date whenSessionCreated) {
		this.whenSessionCreated = whenSessionCreated;
	}

	/**
	 * Returns the value of property "whenSessionUpdated".
	 * The time stamp when the book on request is processed by on prem CAD
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenSessionUpdated() {
		return whenSessionUpdated;
	}

	/**
	 * Updates the value of property "whenSessionUpdated".
	 */
	public void setWhenSessionUpdated(Date whenSessionUpdated) {
		this.whenSessionUpdated = whenSessionUpdated;
	}

}
