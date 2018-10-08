package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Equipment implements Serializable {
    /** Default serial version ID. */
    private static final long serialVersionUID = 1L;

    private Lookup agency;

    private Lookup type;

    private String equipmentItemName;

    private String id;

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
     * Returns the value of property "type". 
     * 
     */
    public Lookup getType() {
        return type;
    }

    /**
     * Updates the value of property "type". 
     */
    public void setType(Lookup type) {
        this.type = type;
    }

    /**
     * Returns the value of property "equipmentItemName". 
     * Equipment item name
     */
    public String getEquipmentItemName() {
        return equipmentItemName;
    }

    /**
     * Updates the value of property "equipmentItemName". 
     */
    public void setEquipmentItemName(String equipmentItemName) {
        this.equipmentItemName = equipmentItemName;
    }

    /**
     * Returns the value of property "id". 
     * Equipment Id
     */
    public String getId() {
        return id;
    }

    /**
     * Updates the value of property "id". 
     */
    public void setId(String id) {
        this.id = id;
    }

}
