package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UnitHandle implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private String key;

    private String agency;

    private String callSign;

    private String shiftId;

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
     * Returns the value of property "agency". 
     * Unit Agency Id
     */
    public String getAgency() {
        return agency;
    }

    /**
     * Updates the value of property "agency". 
     */
    public void setAgency(String agency) {
        this.agency = agency;
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
        this.shiftId = shiftId;
    }

}
