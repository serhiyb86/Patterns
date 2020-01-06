package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.serializer.LocalDateTimeSerializer;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Unit implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String key;

	private String disciplineKey;

	private String agencyKey;

	private String callSign;

	private String shiftId;

	private String unitDescription;

	private String statusKey;

	private Long statusAction;

	private String nextStatusKey;

	private String whenStatusDeclared;

	private String lastKnownLocation;

	private Address currentLocation;

	private String destinationLocation;

	private GpsData currentGpsData;

	private String lastKnownUpdate;

	private Long minimumStaffingLevel;

	private Boolean isPersonnelOutsideUnit;

	private IncidentHandle assignedIncident;

	private List<IncidentHandle> stackedIncidents = new ArrayList<IncidentHandle>();

	private List<Personnel> assignedPersonnel = new ArrayList<Personnel>();

	private List<Capability> incidentQualification = new ArrayList<Capability>();

	private List<Capability> capabilities = new ArrayList<Capability>();

	private List<Equipment> equipment = new ArrayList<Equipment>();

	private JurisdictionalAssignment jurisdictionalAssignment;

	private Fleet fleetAssignment;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime whenStatusExpires;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime whenCreated;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime whenUpdated;

	/**
	 * Returns the value of property "customerId".
	 * Use for DB partition
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
	 * Returns the value of property "key".
	 * Unit Key
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
	 * Returns the value of property "disciplineKey".
	 * Agency Type Key [Mapped To Codes Table:-cad.agencyType]
	 */
	public String getDisciplineKey() {
		return disciplineKey;
	}

	/**
	 * Updates the value of property "disciplineKey".
	 */
	public void setDisciplineKey(String disciplineKey) {
		this.disciplineKey = OneRmsHashUtils.convertCodeToOneRmsFormat(disciplineKey);
	}

	/**
	 * Returns the value of property "agencyKey".
	 * Agency Id of Unit
	 */
	public String getAgencyKey() {
		return agencyKey;
	}

	/**
	 * Updates the value of property "agencyKey".
	 */
	public void setAgencyKey(String agencyKey) {
		this.agencyKey = OneRmsHashUtils.convertCodeToOneRmsFormat(agencyKey);
	}

	/**
	 * Returns the value of property "callSign".
	 * Call Sign of unit
	 */
	public String getCallSign() {
		return callSign;
	}

	/**
	 * Updates the value of property "callSign".
	 */
	public void setCallSign(String callSign) {
		this.callSign = callSign;
	}

	/**
	 * Returns the value of property "shiftId".
	 * Shift Id of Unit
	 */
	public String getShiftId() {
		return shiftId;
	}

	/**
	 * Updates the value of property "shiftId".
	 */
	public void setShiftId(String shiftId) {
		this.shiftId = OneRmsHashUtils.convertCodeToOneRmsFormat(shiftId);
	}

	/**
	 * Returns the value of property "unitDescription".
	 * Unit description
	 */
	public String getUnitDescription() {
		return unitDescription;
	}

	/**
	 * Updates the value of property "unitDescription".
	 */
	public void setUnitDescription(String unitDescription) {
		this.unitDescription = unitDescription;
	}

	/**
	 * Returns the value of property "statusKey".
	 * Current Status Key
	 */
	public String getStatusKey() {
		return statusKey;
	}

	/**
	 * Updates the value of property "statusKey".
	 */
	public void setStatusKey(String statusKey) {
		this.statusKey = OneRmsHashUtils.convertCodeToOneRmsFormat(statusKey);
	}

	/**
	 * Returns the value of property "statusAction".
	 * Current Status Action
	 */
	public Long getStatusAction() {
		return statusAction;
	}

	/**
	 * Updates the value of property "statusAction".
	 */
	public void setStatusAction(Long statusAction) {
		this.statusAction = statusAction;
	}

	/**
	 * Returns the value of property "nextStatusKey".
	 * The next Status in the workflow [Mapped To Codes Table:-cad.unit.status]
	 */
	public String getNextStatusKey() {
		return nextStatusKey;
	}

	/**
	 * Updates the value of property "nextStatusKey".
	 */
	public void setNextStatusKey(String nextStatusKey) {
		this.nextStatusKey = OneRmsHashUtils.convertCodeToOneRmsFormat(nextStatusKey);
	}

	/**
	 * Returns the value of property "whenStatusDeclared".
	 * Date and Time the Unit entered into the current status
	 */
	public String getWhenStatusDeclared() {
		return whenStatusDeclared;
	}

	/**
	 * Updates the value of property "whenStatusDeclared".
	 */
	public void setWhenStatusDeclared(String whenStatusDeclared) {
		this.whenStatusDeclared = whenStatusDeclared;
	}


	/**
	 * Returns the value of property "lastKnownLocation".
	 * Last known location
	 */
	public String getLastKnownLocation() {
		return lastKnownLocation;
	}

	/**
	 * Updates the value of property "lastKnownLocation".
	 */
	public void setLastKnownLocation(String lastKnownLocation) {
		this.lastKnownLocation = lastKnownLocation;
	}

	/**
	 * Returns the value of property "currentLocation".
	 *
	 */
	public Address getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * Updates the value of property "currentLocation".
	 */
	public void setCurrentLocation(Address currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * Returns the value of property "destinationLocation".
	 * Destination location
	 */
	public String getDestinationLocation() {
		return destinationLocation;
	}

	/**
	 * Updates the value of property "destinationLocation".
	 */
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	/**
	 * Returns the value of property "currentGpsData".
	 *
	 */
	public GpsData getCurrentGpsData() {
		return currentGpsData;
	}

	/**
	 * Updates the value of property "currentGpsData".
	 */
	public void setCurrentGpsData(GpsData currentGpsData) {
		this.currentGpsData = currentGpsData;
	}

	/**
	 * Returns the value of property "lastKnownUpdate".
	 * Most recent update (comment) reported by the Unit
	 */
	public String getLastKnownUpdate() {
		return lastKnownUpdate;
	}

	/**
	 * Updates the value of property "lastKnownUpdate".
	 */
	public void setLastKnownUpdate(String lastKnownUpdate) {
		this.lastKnownUpdate = lastKnownUpdate;
	}

	/**
	 * Returns the value of property "minimumStaffingLevel".
	 * Minimum number of personnel required to operate the Unit"
	 */
	public Long getMinimumStaffingLevel() {
		return minimumStaffingLevel;
	}

	/**
	 * Updates the value of property "minimumStaffingLevel".
	 */
	public void setMinimumStaffingLevel(Long minimumStaffingLevel) {
		this.minimumStaffingLevel = minimumStaffingLevel;
	}

	/**
	 * Returns the value of property "isPersonnelOutsideUnit".
	 * indicates if the unit's personnel is inside (false) or outside (true) of the vehicle
	 */
	public Boolean getIsPersonnelOutsideUnit() {
		return isPersonnelOutsideUnit;
	}

	/**
	 * Updates the value of property "isPersonnelOutsideUnit".
	 */
	public void setIsPersonnelOutsideUnit(Boolean isPersonnelOutsideUnit) {
		this.isPersonnelOutsideUnit = isPersonnelOutsideUnit;
	}

	/**
	 * Returns the value of property "assignedIncident".
	 *
	 */
	public IncidentHandle getAssignedIncident() {
		return assignedIncident;
	}

	/**
	 * Updates the value of property "assignedIncident".
	 */
	public void setAssignedIncident(IncidentHandle assignedIncident) {
		this.assignedIncident = assignedIncident;
	}

	/**
	 * Returns the value of property "stackedIncidents".
	 * List of incident to which the unit is stacked
	 */
	public List<IncidentHandle> getStackedIncidents() {
		return stackedIncidents;
	}

	/**
	 * Updates the value of property "stackedIncidents".
	 */
	public void setStackedIncidents(List<IncidentHandle> stackedIncidents) {
		this.stackedIncidents = stackedIncidents;
	}

	/**
	 * Returns the value of property "assignedPersonnel".
	 * Personnel currently assigned to the Unit
	 */
	public List<Personnel> getAssignedPersonnel() {
		return assignedPersonnel;
	}

	/**
	 * Updates the value of property "assignedPersonnel".
	 */
	public void setAssignedPersonnel(List<Personnel> assignedPersonnel) {
		this.assignedPersonnel = assignedPersonnel;
	}

	/**
	 * Returns the value of property "incidentQualification".
	 * Working capabilities of the Unit on the currently assigned incident
	 */
	public List<Capability> getIncidentQualification() {
		return incidentQualification;
	}

	/**
	 * Updates the value of property "incidentQualification".
	 */
	public void setIncidentQualification(List<Capability> incidentQualification) {
		this.incidentQualification = incidentQualification;
	}

	/**
	 * Returns the value of property "capabilities".
	 * Capabilities assigned to the Unit
	 */
	public List<Capability> getCapabilities() {
		return capabilities;
	}

	/**
	 * Updates the value of property "capabilities".
	 */
	public void setCapabilities(List<Capability> capabilities) {
		this.capabilities = capabilities;
	}

	/**
	 * Returns the value of property "equipment".
	 * Equipment assigned to the Unit
	 */
	public List<Equipment> getEquipment() {
		return equipment;
	}

	/**
	 * Updates the value of property "equipment".
	 */
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	/**
	 * Returns the value of property "jurisdictionalAssignment".
	 *
	 */
	public JurisdictionalAssignment getJurisdictionalAssignment() {
		return jurisdictionalAssignment;
	}

	/**
	 * Updates the value of property "jurisdictionalAssignment".
	 */
	public void setJurisdictionalAssignment(JurisdictionalAssignment jurisdictionalAssignment) {
		this.jurisdictionalAssignment = jurisdictionalAssignment;
	}

	/**
	 * Returns the value of property "fleetAssignment".
	 *
	 */
	public Fleet getFleetAssignment() {
		return fleetAssignment;
	}

	/**
	 * Updates the value of property "fleetAssignment".
	 */
	public void setFleetAssignment(Fleet fleetAssignment) {
		this.fleetAssignment = fleetAssignment;
	}

	/**
	 * Returns the value of property "whenStatusExpires".
	 * Indicates time when status expires
	 */
	public LocalDateTime getWhenStatusExpires() {
		return whenStatusExpires;
	}

	/**
	 * Updates the value of property "whenStatusExpires".
	 */
	public void setWhenStatusExpires(LocalDateTime whenStatusExpires) {
		this.whenStatusExpires = whenStatusExpires;
	}

	/**
	 * Returns the value of property "whenCreated".
	 * When this unit record was created
	 */
	public LocalDateTime getWhenCreated() {
		return whenCreated;
	}

	/**
	 * Updates the value of property "whenCreated".
	 */
	public void setWhenCreated(LocalDateTime whenCreated) {
		this.whenCreated = whenCreated;
	}

	/**
	 * Returns the value of property "whenUpdated".
	 * Last time the Unit record was updated
	 */
	public LocalDateTime getWhenUpdated() {
		return whenUpdated;
	}

	/**
	 * Updates the value of property "whenUpdated".
	 */
	public void setWhenUpdated(LocalDateTime whenUpdated) {
		this.whenUpdated = whenUpdated;
	}
}
