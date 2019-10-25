package com.motorola.models.representation;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motorola.models.serializer.ZonedDateTimeSerializer;

import java.io.Serializable;
import java.time.ZonedDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(NON_NULL)
public class GpsData implements Serializable {

	/** Default serial version ID. */
	private static final long serialVersionUID = 1L;

	private Double latitude;

	private Double longitude;

	private String heading;

	private String speed;

	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private ZonedDateTime whenReported;

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
	public ZonedDateTime getWhenReported() {
		return whenReported;
	}

	/**
	 * Updates the value of property "whenReported".
	 */
	public void setWhenReported(ZonedDateTime whenReported) {
		this.whenReported = whenReported;
	}

}
