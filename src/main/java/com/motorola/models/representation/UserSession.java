package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.serializer.ZonedDateTimeSerializer;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user session - stored in Cosmos DB
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UserSession implements Serializable {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String sessionId;

	private PersonnelHandle user;

	private DeviceHandle device;

	private RoleHandle role;

	private List<String> apiAccessList = new ArrayList<>();

	private MonitorAreas monitorAreas;

	private AdditionalInfo additionalInfo;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime whenSessionCreated;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime whenSessionUpdated;

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
	 * Returns the value of property "user".
	 *
	 */
	public PersonnelHandle getUser() {
		return user;
	}

	/**
	 * Updates the value of property "user".
	 */
	public void setUser(PersonnelHandle user) {
		this.user = user;
	}

	/**
	 * Returns the value of property "device".
	 *
	 */
	public DeviceHandle getDevice() {
		return device;
	}

	/**
	 * Updates the value of property "device".
	 */
	public void setDevice(DeviceHandle device) {
		this.device = device;
	}

	/**
	 * Returns the value of property "role".
	 *
	 */
	public RoleHandle getRole() {
		return role;
	}

	/**
	 * Updates the value of property "role".
	 */
	public void setRole(RoleHandle role) {
		this.role = role;
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
	public ZonedDateTime getWhenSessionCreated() {
		return whenSessionCreated;
	}

	/**
	 * Updates the value of property "whenSessionCreated".
	 */
	public void setWhenSessionCreated(ZonedDateTime whenSessionCreated) {
		this.whenSessionCreated = whenSessionCreated;
	}

	/**
	 * Returns the value of property "whenSessionUpdated".
	 * The time stamp when the book on request is processed by on prem CAD
	 */
	public ZonedDateTime getWhenSessionUpdated() {
		return whenSessionUpdated;
	}

	/**
	 * Updates the value of property "whenSessionUpdated".
	 */
	public void setWhenSessionUpdated(ZonedDateTime whenSessionUpdated) {
		this.whenSessionUpdated = whenSessionUpdated;
	}

}
