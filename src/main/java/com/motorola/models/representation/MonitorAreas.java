package com.motorola.models.representation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.motorola.utils.OneRmsHashUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * Monitor areas - support monitor subset of units in the station if specified
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class MonitorAreas implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Set<String> areaKeys = new HashSet<>();


	/**
	 * Returns the value of property "areaKeys".
	 * Areas Key [Mapped To Codes Table:-cad.jurisdiction.areas]
	 */
	public Set<String> getAreaKeys() {
		return areaKeys;
	}

	/**
	 * Updates the value of property "areaKeys".
	 */
	public void setAreaKeys(Set<String> areaKeys) {
		if (areaKeys != null && !areaKeys.isEmpty()) {
			areaKeys = areaKeys.stream()
				.map(OneRmsHashUtils::convertCodeToOneRmsFormat)
				.collect(Collectors.toSet());
		}
		this.areaKeys = areaKeys;
	}



}
