package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UpdateEmergencyIncident implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private EmergencyIncident old;

    private EmergencyIncident __new;

    /**
     * Returns the value of property "old". 
     * 
     */
    public EmergencyIncident getOld() {
        return old;
    }

    /**
     * Updates the value of property "old". 
     */
    public void setOld(EmergencyIncident old) {
        this.old = old;
    }

    /**
     * Returns the value of property "__new". 
     * 
     */
    @JsonProperty("new")
    public EmergencyIncident get__new() {
        return __new;
    }

    /**
     * Updates the value of property "__new". 
     */
    public void set__new(EmergencyIncident __new) {
        this.__new = __new;
    }

}
