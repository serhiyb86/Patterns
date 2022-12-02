package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class Jurisdiction implements Serializable {
	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

    private String areaKey;

    private String sectorKey;

    private String beatKey;

    /**
     * Returns the value of property "areaKey". 
     * Area [Mapped To Codes Table:-cad.jurisdiction.area]
     */
    public String getAreaKey() {
        return areaKey;
    }

    /**
     * Updates the value of property "areaKey". 
     */
    public void setAreaKey(String areaKey) {
        this.areaKey = OneRmsHashUtils.convertCodeToOneRmsFormat(areaKey);
    }

    /**
     * Returns the value of property "sectorKey". 
     * Sector/District [Mapped To Codes Table:-cad.jurisdiction.sector]
     */
    public String getSectorKey() {
        return sectorKey;
    }

    /**
     * Updates the value of property "sectorKey". 
     */
    public void setSectorKey(String sectorKey) {
        this.sectorKey = sectorKey;
    }

    /**
     * Returns the value of property "beatKey". 
     * Beat/Zone [Mapped To Codes Table:-cad.jurisdiction.beat]
     */
    public String getBeatKey() {
        return beatKey;
    }

    /**
     * Updates the value of property "beatKey". 
     */
    public void setBeatKey(String beatKey) {
        this.beatKey = beatKey;
    }

}
