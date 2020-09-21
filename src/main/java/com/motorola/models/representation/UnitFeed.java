package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UnitFeed implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private String key;

    private String agencyAlias;

    private String callSign;

    private String shiftId;

    private String agencyKey;

    private String homeAreaKey;

    private String homeStationKey;

    private List<PersonnelHandle> assignedPersonnel;

    private String statusKey;

    private String whenStatusDeclared;

    private String statusCategoryKey;



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
     * Returns the value of property "agencyAlias".
     * Unit Agency Name
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
     * Returns the value of property "callSign". 
     * Unit Call Sign
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
     * Unit Shift Id
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
     * Returns the value of property "agencyKey".
     * Unit agencyKey
     */
    public String getAgencyKey() { return agencyKey; }

    /**
     * Updates the value of property "agencyKey".
     */
    public void setAgencyKey(String agencyKey) {
        this.agencyKey = OneRmsHashUtils.convertCodeToOneRmsFormat(agencyKey); }

    /**
     * Returns the value of property "homeAreaKey".
     * Unit homeAreaKey
     */
    public String getHomeAreaKey() { return homeAreaKey; }

    /**
     * Updates the value of property "homeAreaKey".
     */
    public void setHomeAreaKey(String homeAreaKey) {
        this.homeAreaKey = OneRmsHashUtils.convertCodeToOneRmsFormat(homeAreaKey); }

    /**
     * Returns the value of property "homeStationKey".
     * Unit homeStationKey
     */
    public String getHomeStationKey() { return homeStationKey; }

    /**
     * Updates the value of property "homeStationKey".
     */
    public void setHomeStationKey(String homeStationKey) {
        this.homeStationKey = OneRmsHashUtils.convertCodeToOneRmsFormat(homeStationKey);
    }

    /**
     * Returns the value of property "assignedPersonnel".
     * Unit assignedPersonnel
     */
    public List<PersonnelHandle> getAssignedPersonnel() { return assignedPersonnel; }

    /**
     * Updates the value of property "assignedPersonnel".
     */
    public void setAssignedPersonnel(List<PersonnelHandle> assignedPersonnel) {
        this.assignedPersonnel = assignedPersonnel;
    }

    /**
     * Returns the value of property "statusKey".
     * Unit statusKey
     */
    public String getStatusKey() { return statusKey; }

    /**
     * Updates the value of property "statusKey".
     */
    public void setStatusKey(String statusKey) {
        this.statusKey = OneRmsHashUtils.convertCodeToOneRmsFormat(statusKey); }

    /**
     * Returns the value of property "whenStatusDeclared".
     * Unit whenStatusDeclared
     */
    public String getWhenStatusDeclared() { return whenStatusDeclared; }

    /**
     * Updates the value of property "whenStatusDeclared".
     */
    public void setWhenStatusDeclared(String whenStatusDeclared) {
        this.whenStatusDeclared = whenStatusDeclared;
    }

    /**
     * Returns the value of property "statusCategoryKey".
     * Unit statusCategoryKey
     */
    public String getStatusCategoryKey() { return statusCategoryKey; }

    /**
     * Updates the value of property "statusCategoryKey".
     */
    public void setStatusCategoryKey(String statusCategoryKey) {
        this.statusCategoryKey = statusCategoryKey;
    }
}
