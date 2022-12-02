/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.translation.v2019_1_15_0.mappers.notification;

import com.google.gson.JsonObject;
import com.motorola.models.representation.ResponseNotification;

import java.util.Collections;

/**
 * Mapper for converting Json Object with appropriate data to the {@link  ResponseNotification} object.
 */
public class ResponseNotificationMapper extends AbstractNotificationMapper<ResponseNotification> {

	public ResponseNotificationMapper() {
		super(Collections.emptyMap());
	}

	/**
	 * Creates {@link ResponseNotification} object and sets mapped data from json to it.
	 *
	 * @param data json data.
	 * @return {@link ResponseNotification} object with mapped data.
	 */
	public ResponseNotification createAndMapToResponseNotification(JsonObject data) {
		ResponseNotification notification = new ResponseNotification();
		return mapToModel(data, notification);
	}

}
