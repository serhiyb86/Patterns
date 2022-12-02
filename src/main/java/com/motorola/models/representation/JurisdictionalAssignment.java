package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class JurisdictionalAssignment implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private List<String> areaKeys = new ArrayList<>();

	private List<String> sectorKeys = new ArrayList<>();

	private List<String> beatKeys = new ArrayList<>();

	private String homeStationKey;

	private String assignedStationKey;

	private String homeAreaKey;

	/**
	 * Returns the value of property "areaKeys".
	 * Areas [Mapped To Codes Table:-cad.jurisdiction.area]
	 */
	public List<String> getAreaKeys() {
		return areaKeys;
	}

	/**
	 * Updates the value of property "areaKeys".
	 */
	public void setAreaKeys(List<String> areaKeys) {
		if (areaKeys != null && !areaKeys.isEmpty()) {
			areaKeys = areaKeys.stream()
				.map(OneRmsHashUtils::convertCodeToOneRmsFormat)
				.collect(Collectors.toList());
		}
		this.areaKeys = areaKeys;
	}

	/**
	 * Returns the value of property "sectorKeys".
	 * Sectors [Mapped To Codes Table:-cad.jurisdiction.sector]
	 */
	public List<String> getSectorKeys() {
		return sectorKeys;
	}

	/**
	 * Updates the value of property "sectorKeys".
	 */
	public void setSectorKeys(List<String> sectorKeys) {
		this.sectorKeys = sectorKeys;
	}

	/**
	 * Returns the value of property "beatKeys".
	 * Beats/zones [Mapped To Codes Table:-cad.jurisdiction.beat]
	 */
	public List<String> getBeatKeys() {
		return beatKeys;
	}

	/**
	 * Updates the value of property "beatKeys".
	 */
	public void setBeatKeys(List<String> beatKeys) {
		if (beatKeys != null && !beatKeys.isEmpty()) {
			beatKeys = beatKeys.stream()
				.map(OneRmsHashUtils::convertCodeToOneRmsFormat)
				.collect(Collectors.toList());
		}
		this.beatKeys = beatKeys;
	}

	/**
	 * Returns the value of property "homeStationKey".
	 * Home (Default) Station Key [Mapped To Codes Table:-cad.jurisdiction.station]
	 */
	public String getHomeStationKey() {
		return homeStationKey;
	}

	/**
	 * Updates the value of property "homeStationKey".
	 */
	public void setHomeStationKey(String homeStationKey) {
		this.homeStationKey = homeStationKey;
	}

	/**
	 * Returns the value of property "assignedStationKey".
	 * Currently assigned Station Key [Mapped To Codes Table:-cad.jurisdiction.station]
	 */
	public String getAssignedStationKey() {
		return assignedStationKey;
	}

	/**
	 * Updates the value of property "assignedStationKey".
	 */
	public void setAssignedStationKey(String assignedStationKey) {
		this.assignedStationKey = assignedStationKey;
	}


	/**
	 * Returns the value of property "homeAreaKey".
	 */
	public String getHomeAreaKey() { return homeAreaKey; }

	/**
	 * Updates the value of property "homeAreaKey".
	 */
	public void setHomeAreaKey(String homeAreaKey) {
		if (StringUtils.isNotBlank(homeAreaKey)) {
			homeAreaKey = OneRmsHashUtils.convertCodeToOneRmsFormat(homeAreaKey);
		}
		this.homeAreaKey = homeAreaKey;
	}


}