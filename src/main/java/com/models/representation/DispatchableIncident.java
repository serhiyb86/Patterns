package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class DispatchableIncident implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String alias;

	private Lookup discipline;

	private Lookup agency;

	private Nature nature;

	private String priority;

	private Lookup status;

	private String whenStatusDeclared;

	private Location location;

	private List<IncidentComment> comments = new ArrayList<IncidentComment>();

	private List<Disposition> dispositions = new ArrayList<com.models.representation.Disposition>();

	private Lookup proqaDeterminant;

	private Boolean isScheduled;

	private Lookup incidentSource;

	private List<UnitHandle> assignedUnits = new ArrayList<com.models.representation.UnitHandle>();

	private UnitHandle primaryUnit;

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
	 * Returns the value of property "discipline".
	 *
	 */
	public Lookup getDiscipline() {
		return discipline;
	}

	/**
	 * Updates the value of property "discipline".
	 */
	public void setDiscipline(Lookup discipline) {
		this.discipline = discipline;
	}

	/**
	 * Returns the value of property "agency".
	 *
	 */
	public Lookup getAgency() {
		return agency;
	}

	/**
	 * Updates the value of property "agency".
	 */
	public void setAgency(Lookup agency) {
		this.agency = agency;
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
	 * Returns the value of property "status".
	 *
	 */
	public Lookup getStatus() {
		return status;
	}

	/**
	 * Updates the value of property "status".
	 */
	public void setStatus(Lookup status) {
		this.status = status;
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
	 * Returns the value of property "proqaDeterminant".
	 *
	 */
	public Lookup getProqaDeterminant() {
		return proqaDeterminant;
	}

	/**
	 * Updates the value of property "proqaDeterminant".
	 */
	public void setProqaDeterminant(Lookup proqaDeterminant) {
		this.proqaDeterminant = proqaDeterminant;
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
	 * Returns the value of property "incidentSource".
	 *
	 */
	public Lookup getIncidentSource() {
		return incidentSource;
	}

	/**
	 * Updates the value of property "incidentSource".
	 */
	public void setIncidentSource(Lookup incidentSource) {
		this.incidentSource = incidentSource;
	}

	/**
	 * Returns the value of property "assignedUnits".
	 * Responding Units
	 */
	public List<UnitHandle> getAssignedUnits() {
		return assignedUnits;
	}

	/**
	 * Updates the value of property "assignedUnits".
	 */
	public void setAssignedUnits(List<UnitHandle> assignedUnits) {
		this.assignedUnits = assignedUnits;
	}

	/**
	 * Returns the value of property "primaryUnit".
	 *
	 */
	public UnitHandle getPrimaryUnit() {
		return primaryUnit;
	}

	/**
	 * Updates the value of property "primaryUnit".
	 */
	public void setPrimaryUnit(UnitHandle primaryUnit) {
		this.primaryUnit = primaryUnit;
	}

}
