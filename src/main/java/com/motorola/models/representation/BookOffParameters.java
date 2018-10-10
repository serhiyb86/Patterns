package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class BookOffParameters implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Boolean isKeepUnitActiveOnCAD;

	/**
	 * Returns the value of property "isKeepUnitActiveOnCAD".
	 *
	 */
	public Boolean getIsKeepUnitActiveOnCAD() {
		return isKeepUnitActiveOnCAD;
	}

	/**
	 * Updates the value of property "isKeepUnitActiveOnCAD".
	 */
	public void setIsKeepUnitActiveOnCAD(Boolean isKeepUnitActiveOnCAD) {
		this.isKeepUnitActiveOnCAD = isKeepUnitActiveOnCAD;
	}

}
