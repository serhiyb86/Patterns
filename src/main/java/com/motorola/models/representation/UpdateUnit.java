package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class UpdateUnit implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private Unit old;

    private Unit __new;

    /**
     * Returns the value of property "old". 
     * 
     */
    public Unit getOld() {
        return old;
    }

    /**
     * Updates the value of property "old". 
     */
    public void setOld(Unit old) {
        this.old = old;
    }

    /**
     * Returns the value of property "__new". 
     * 
     */
    @JsonProperty("new")
    public Unit get__new() {
        return __new;
    }

    /**
     * Updates the value of property "__new". 
     */
    public void set__new(Unit __new) {
        this.__new = __new;
    }

}
