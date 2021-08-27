/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.unit;

import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.GpsData;
import com.motorola.translation.setter.DoubleSetter;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;
import com.motorola.translation.v2019_1_15_0.mappers.GenericMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with Unit data to the {@link com.motorola.models.representation.GpsData} object.
 */
public class GpsDataMapper extends GenericMapper<GpsData> {

	private static final Map<String, Setter<GpsData>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.Unit.GpsData.LATITUDE, new DoubleSetter<>(GpsData::setLatitude));
		setters.put(InterfaceConstants.Unit.GpsData.LONGITUDE, new DoubleSetter<>(GpsData::setLongitude));
		setters.put(InterfaceConstants.Unit.GpsData.HEADING, new StringSetter<>(GpsData::setHeading));
		setters.put(InterfaceConstants.Unit.GpsData.SPEED, new StringSetter<>(GpsData::setSpeed));
		setters.put(InterfaceConstants.Unit.GpsData.WHEN_REPORTED, new StringSetter<>(GpsData::setWhenReported));
	}


	public GpsDataMapper() {
		super(setters);
	}
}
