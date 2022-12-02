package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DispatchableIncident implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String key;

	private String alias;

	private String disciplineKey;

	private String agencyKey;

	private Nature nature;

	private String priority;

	private String statusKey;

	private String statusCategory;

	private String whenStatusDeclared;

	private String whenStatusExpires;

	private Location location;

	private List<IncidentComment> comments = new ArrayList<>();

	private List<Disposition> dispositions = new ArrayList<>();

	private String proqaDeterminantKey;

	private Boolean isScheduled;

	private String incidentSourceKey;

	private List<UnitFeed> assignedUnits = new ArrayList<>();

	private UnitFeed primaryUnit;

	private List<ReportNumber> reportNumbers;

	private int alertCount;

	private String agencyAlias;

	private String whenFirstUnitArrived;

	private String whenFirstUnitEnrouted;

	private String createdUserAlias;

	private String createdUserAgencyAlias;

	private List<String> reportingDistrictNames;

	private List<HistoryTransaction> cadCloudHistoryTransactions;

	public int getAlertCount() {
		return alertCount;
	}

	public void setAlertCount(int alertCount) {
		this.alertCount = alertCount;
	}

	/**
	 * Returns the value of property "key".
	 * Dispatchable Incident Key
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
	 * Alternate Incident Id
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
		this.disciplineKey = disciplineKey;
	}

	/**
	 * Returns the value of property "agencyKey".
	 * Agency Key [Mapped To Codes Table:-cad.agency]
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
	 * Returns the value of property "nature".
	 *
	 */
	public Nature getNature() {
		return nature;
	}

	/**
	 * Updates the value of property "nature".
	 */
	public void setNature(Nature nature) {
		this.nature = nature;
	}

	/**
	 * Returns the value of property "priority".
	 * Priority of the incident
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * Updates the value of property "priority".
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * Returns the value of property "statusKey".
	 * Current Status Key [Mapped To Codes Table:-cad.dispatchIncident.status]
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
	 * Returns the value of property "whenStatusDeclared".
	 * Time the incident entered into the current status
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
	 * Returns the value of property "location".
	 *
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Updates the value of property "location".
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Returns the value of property "comments".
	 * Incident comments
	 */
	public List<IncidentComment> getComments() {
		return comments;
	}

	/**
	 * Updates the value of property "comments".
	 */
	public void setComments(List<IncidentComment> comments) {
		this.comments = comments;
	}

	/**
	 * Returns the value of property "dispositions".
	 * Assigned dispositions
	 */
	public List<Disposition> getDispositions() {
		return dispositions;
	}

	/**
	 * Updates the value of property "dispositions".
	 */
	public void setDispositions(List<Disposition> dispositions) {
		this.dispositions = dispositions;
	}

	/**
	 * Returns the value of property "proqaDeterminantKey".
	 * ProQA Determinant Key [Mapped To Codes Table:-cad.dispatchIncident.proqaDeterminant]
	 */
	public String getProqaDeterminantKey() {
		return proqaDeterminantKey;
	}

	/**
	 * Updates the value of property "proqaDeterminantKey".
	 */
	public void setProqaDeterminantKey(String proqaDeterminantKey) {
		this.proqaDeterminantKey = OneRmsHashUtils.convertCodeToOneRmsFormat(proqaDeterminantKey);
	}

	/**
	 * Returns the value of property "isScheduled".
	 * indicates if the incident is scheduled (true)
	 */
	public Boolean getIsScheduled() {
		return isScheduled;
	}

	/**
	 * Updates the value of property "isScheduled".
	 */
	public void setIsScheduled(Boolean isScheduled) {
		this.isScheduled = isScheduled;
	}

	/**
	 * Returns the value of property "incidentSourceKey".
	 * Incident Source Key that indicates how the incident was reported [Mapped To Codes Table:-cad.dispatchIncident.incidentSource]
	 */
	public String getIncidentSourceKey() {
		return incidentSourceKey;
	}

	/**
	 * Updates the value of property "incidentSourceKey".
	 */
	public void setIncidentSourceKey(String incidentSourceKey) {
		this.incidentSourceKey = OneRmsHashUtils.convertCodeToOneRmsFormat(incidentSourceKey);
	}

	/**
	 * Returns the value of property "assignedUnits".
	 * Responding Units
	 */
	public List<UnitFeed> getAssignedUnits() {
		return assignedUnits;
	}

	/**
	 * Updates the value of property "assignedUnits".
	 */
	public void setAssignedUnits(List<UnitFeed> assignedUnits) {
		this.assignedUnits = assignedUnits;
	}

	/**
	 * Returns the value of property "primaryUnit".
	 *
	 */
	public UnitFeed getPrimaryUnit() {
		return primaryUnit;
	}

	/**
	 * Updates the value of property "primaryUnit".
	 */
	public void setPrimaryUnit(UnitFeed primaryUnit) {
		this.primaryUnit = primaryUnit;
	}

	/**
	 * Returns the value of property "reportNumbers"
	 */
	public List<ReportNumber> getReportNumbers() {
		return reportNumbers;
	}

	/**
	 * Updates the value of property "reportNumbers"
	 */
	public void setReportNumbers(List<ReportNumber> reportNumbers) {
		this.reportNumbers = reportNumbers;
	}

	public String getStatusCategory() {
		return statusCategory;
	}

	public void setStatusCategory(String statusCategory) {
		this.statusCategory = statusCategory;
	}

	public String getWhenStatusExpires() {
		return whenStatusExpires;
	}

	public void setWhenStatusExpires(String whenStatusExpires) {
		this.whenStatusExpires = whenStatusExpires;
	}

	/**
	 * Returns the value of property "agencyAlias"
	 */
	public String getAgencyAlias() { return agencyAlias; }

	/**
	 * Updates the value of property "agencyAlias"
	 */
	public void setAgencyAlias(String agencyAlias) { this.agencyAlias = agencyAlias; }

	/**
	 * Returns the value of property "whenFirstUnitArrived"
	 */
	public String getWhenFirstUnitArrived() { return whenFirstUnitArrived; }

	/**
	 * Updates the value of property "whenFirstUnitArrived"
	 */
	public void setWhenFirstUnitArrived(String whenFirstUnitArrived) { this.whenFirstUnitArrived = whenFirstUnitArrived; }

	/**
	 * Returns the value of property "whenFirstUnitEnrouted"
	 */
	public String getWhenFirstUnitEnrouted() { return whenFirstUnitEnrouted; }

	/**
	 * Updates the value of property "whenFirstUnitEnrouted"
	 */
	public void setWhenFirstUnitEnrouted(String whenFirstUnitEnrouted) {
		this.whenFirstUnitEnrouted = whenFirstUnitEnrouted;
	}

	/**
	 * Returns the value of property "createdUserAlias"
	 */
	public String getCreatedUserAlias() { return createdUserAlias; }

	/**
	 * Updates the value of property "createdUserAlias"
	 */
	public void setCreatedUserAlias(String createdUserAlias) { this.createdUserAlias = createdUserAlias; }

	/**
	 * Returns the value of property "createdUserAgencyAlias"
	 */
	public String getCreatedUserAgencyAlias() { return createdUserAgencyAlias; }

	/**
	 * Updates the value of property "createdUserAgencyAlias"
	 */
	public void setCreatedUserAgencyAlias(String createdUserAgencyAlias) { this.createdUserAgencyAlias = createdUserAgencyAlias; }

	public List<String> getReportingDistrictNames() {
		return reportingDistrictNames;
	}

	public void setReportingDistrictNames(List<String> reportingDistrictNames) {
		this.reportingDistrictNames = reportingDistrictNames;
	}

	public List<HistoryTransaction> getCadCloudHistoryTransactions() {
		return cadCloudHistoryTransactions;
	}

	public void setCadCloudHistoryTransactions(List<HistoryTransaction> cadCloudHistoryTransactions) {
		this.cadCloudHistoryTransactions = cadCloudHistoryTransactions;
	}
}