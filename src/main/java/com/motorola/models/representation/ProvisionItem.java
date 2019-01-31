package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.models.Config;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Common type for each provisioning data entry - in Cosmos DB
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class ProvisionItem implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String key;

	private String lookupType;

	private String name;

	private String displayText;

	private String agencyKey;

	private String agencyAlias;

	private String agencyTypeKey;

	private String agencyTypeAlias;

	private String parentKey;

	private List<KeyValue> attritues = new ArrayList<KeyValue>();

	private Date whenUpdated;

	private Date whenCreated;

	private Boolean isExpired;

	/**
	 * Returns the value of property "customerId".
	 * Unique customer ID (for DB partition)
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
	 * UID for each lookup entry
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
	 * Returns the value of property "lookupType".
	 * Provision data type
	 */
	public String getLookupType() {
		return lookupType;
	}

	/**
	 * Updates the value of property "lookupType".
	 */
	public void setLookupType(String lookupType) {
		this.lookupType = lookupType;
	}

	/**
	 * Returns the value of property "name".
	 * Displayable ID
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates the value of property "name".
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of property "displayText".
	 * Displayable long name/id
	 */
	public String getDisplayText() {
		return displayText;
	}

	/**
	 * Updates the value of property "displayText".
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	/**
	 * Returns the value of property "agencyKey".
	 * Agency uid for the setting belongs to. Null to be agency type or customer wide
	 */
	public String getAgencyKey() {
		return agencyKey;
	}

	/**
	 * Updates the value of property "agencyKey".
	 */
	public void setAgencyKey(String agencyKey) {
		this.agencyKey = agencyKey;
	}

	/**
	 * Returns the value of property "agencyAlias".
	 * Agency code for the setting belongs to. Null to be agency type or customer wide
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
	 * Returns the value of property "agencyTypeKey".
	 * Agency key uid for the setting belongs to. Null to be agency or customer wide
	 */
	public String getAgencyTypeKey() {
		return agencyTypeKey;
	}

	/**
	 * Updates the value of property "agencyTypeKey".
	 */
	public void setAgencyTypeKey(String agencyTypeKey) {
		this.agencyTypeKey = agencyTypeKey;
	}

	/**
	 * Returns the value of property "agencyTypeAlias".
	 * Agency code for the setting belongs to. Null to be agency or customer wide
	 */
	public String getAgencyTypeAlias() {
		return agencyTypeAlias;
	}

	/**
	 * Updates the value of property "agencyTypeAlias".
	 */
	public void setAgencyTypeAlias(String agencyTypeAlias) {
		this.agencyTypeAlias = agencyTypeAlias;
	}


	/**
	 * Returns the value of property "parentKey".
	 * Key for its parent entity. Null if no association
	 */
	public String getParentKey() {
		return parentKey;
	}

	/**
	 * Updates the value of property "parentKey".
	 */
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	/**
	 * Returns the value of property "attritues".
	 * NameValue pair collection. Varies lookup type by type. Debatable if we want to keep the extensibility of the lookup entry
	 */
	public List<KeyValue> getAttritues() {
		return attritues;
	}

	/**
	 * Returns the value of property "whenUpdated".
	 * Timestamp when the entry was updated
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenUpdated() {
		return whenUpdated;
	}

	/**
	 * Updates the value of property "attritues".
	 */
	public void setAttritues(List<KeyValue> attritues) {
		this.attritues = attritues;
	}

	/**
	 * Updates the value of property "whenUpdated".
	 */
	public void setWhenUpdated(Date whenUpdated) {
		this.whenUpdated = whenUpdated;
	}

	/**
	 * Returns the value of property "whenCreated".
	 * Timestamp when the entry was created
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public java.util.Date getWhenCreated() {
		return whenCreated;
	}

	/**
	 * Updates the value of property "whenCreated".
	 */
	public void setWhenCreated(Date whenCreated) {
		this.whenCreated = whenCreated;
	}

	/**
	 * Returns the value of property "isExpired".
	 * Soft delete flag to disable this entry
	 */
	public Boolean getIsExpired() {
		return isExpired;
	}

	/**
	 * Updates the value of property "isExpired".
	 */
	public void setIsExpired(Boolean isExpired) {
		this.isExpired = isExpired;
	}

}
