package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.serializer.ZonedDateTimeSerializer;
import com.motorola.utils.OneRmsHashUtils;

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

	private String serviceId;

	private String customerId;

	private String sessionId;

	private PersonnelHandle cadUser;

	private String cadUserAgencyKey;

	private DeviceHandle device;

	private String deviceAgencyKey;

	private RoleHandle role;

	private JurisdictionalAssignment assignments;

	private MonitorAreas monitorAreas;

	private List<AccessScope> apiAccessScope = new ArrayList<>();

	private UnitFeed unit;

	private String vehicleId;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime whenSessionCreated;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime whenSessionUpdated;

	/**
	 * Returns the value of property "serviceId".
	 * Indicates the source of the book on request (Mobile Gateway or other Gateway)
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * Updates the value of property "serviceId".
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


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
	 * Returns the value of property "apiAccessScope".
	 * List of API access scopes defined in Security Definitions based on agencies within a customer in Cloud Client API to indicate the access rights to these API calls.
	 */
	public List<AccessScope> getApiAccessScope() {
		return apiAccessScope;
	}

	/**
	 * Updates the value of property "apiAccessScope".
	 */
	public void setApiAccessScope(List<AccessScope> apiAccessScope) {
		this.apiAccessScope = apiAccessScope;
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

	/**
	 * Returns the value of property "cadUser".
	 *
	 */
	public PersonnelHandle getCadUser() {
		return cadUser;
	}

	/**
	 * Updates the value of property "cadUser".
	 */
	public void setCadUser(PersonnelHandle cadUser) {
		this.cadUser = cadUser;
	}

	/**
	 * Returns the value of property "cadUserAgencyKey".
	 * CAD User agency key - [Mapped To Codes Table:-cad.agencies]
	 */
	public String getCadUserAgencyKey() {
		return cadUserAgencyKey;
	}

	/**
	 * Updates the value of property "cadUserAgencyKey".
	 */
	public void setCadUserAgencyKey(String cadUserAgencyKey) {
		this.cadUserAgencyKey = OneRmsHashUtils.convertCodeToOneRmsFormat(cadUserAgencyKey);
	}

	/**
	 * Returns the value of property "deviceAgencyKey".
	 * Device agency key - [Mapped To Codes Table:-cad.agencies]
	 */
	public String getDeviceAgencyKey() {
		return deviceAgencyKey;
	}

	/**
	 * Updates the value of property "deviceAgencyKey".
	 */
	public void setDeviceAgencyKey(String deviceAgencyKey) {
		this.deviceAgencyKey = OneRmsHashUtils.convertCodeToOneRmsFormat(deviceAgencyKey);
	}

	/**
	 * Returns the value of property "assignments".
	 *
	 */
	public JurisdictionalAssignment getAssignments() {
		return assignments;
	}

	/**
	 * Updates the value of property "assignments".
	 */
	public void setAssignments(JurisdictionalAssignment assignments) {
		this.assignments = assignments;
	}

	/**
	 * Returns the value of property "unit".
	 *
	 */
	public UnitFeed getUnit() {
		return unit;
	}

	/**
	 * Updates the value of property "unit".
	 */
	public void setUnit(UnitFeed unit) {
		this.unit = unit;
	}

	/**
	 * Returns the value of property "vehicleId".
	 * Optional if the unit is not assiged to a vehicle ID
	 */
	public String getVehicleId() {
		return vehicleId;
	}

	/**
	 * Updates the value of property "vehicleId".
	 */
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
}
