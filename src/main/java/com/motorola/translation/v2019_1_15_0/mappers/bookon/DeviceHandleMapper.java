/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.bookon;

import com.google.gson.JsonObject;
import com.motorola.constants.InterfaceConstants;
import com.motorola.models.representation.DeviceHandle;
import com.motorola.translation.setter.Setter;
import com.motorola.translation.setter.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper for converting Json Object with appropriate data to the {@link DeviceHandle} object.
 */
public class DeviceHandleMapper {

	private static final Map<String, Setter<DeviceHandle>> setters = new HashMap<>();

	static {
		setters.put(InterfaceConstants.BookOnProperties.DEVICE_KEY, new StringSetter<>(DeviceHandle::setKey));
		setters.put(InterfaceConstants.BookOnProperties.DEVICE_NAME, new StringSetter<>(DeviceHandle::setAlias));
		setters.put(InterfaceConstants.BookOnProperties.DEVICE_AGENCY_ALIAS, new StringSetter<>(DeviceHandle::setAgencyAlias));
	}

	/**
	 * Creates {@link DeviceHandle} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link DeviceHandle} object with mapped data.
	 */
	public DeviceHandle createAndMapDeviceHandle(JsonObject data) {
		DeviceHandle deviceHandle = new DeviceHandle();
		if (data != null) {
			mapToDeviceHandle(data, deviceHandle);
		}
		return deviceHandle;
	}

	/**
	 * Sets mapped data from json to the {@link DeviceHandle} object.
	 *
	 * @param data json data.
	 * @param deviceHandle target object.
	 */
	private void mapToDeviceHandle(JsonObject data, DeviceHandle deviceHandle) {
		setters.forEach((key, value) -> {
			if (data.get(key) != null) {
				value.accept(deviceHandle, data.get(key));
			}
		});
	}

}
