package com.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.models.Config;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class GpsData implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Double latitude;

	private Double longitude;

	private String heading;

	private String speed;

	private Date whenReported;

	/**
	 * Returns the value of property "latitude".
	 * Degrees Latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * Updates the value of property "latitude".
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Returns the value of property "longitude".
	 * Degrees Longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * Updates the value of property "longitude".
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Returns the value of property "heading".
	 * Heading
	 */
	public String getHeading() {
		return heading;
	}

	/**
	 * Updates the value of property "heading".
	 */
	public void setHeading(String heading) {
		this.heading = heading;
	}

	/**
	 * Returns the value of property "speed".
	 * Speed
	 */
	public String getSpeed() {
		return speed;
	}

	/**
	 * Updates the value of property "speed".
	 */
	public void setSpeed(String speed) {
		this.speed = speed;
	}

	/**
	 * Returns the value of property "whenReported".
	 * Date and Time of the last update
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Config.DATETIME_FORMAT)
	public Date getWhenReported() {
		return whenReported;
	}

	/**
	 * Updates the value of property "whenReported".
	 */
	public void setWhenReported(Date whenReported) {
		this.whenReported = whenReported;
	}

}
