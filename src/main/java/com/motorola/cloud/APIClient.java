/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.cloud;

import com.motorola.models.Config;
import com.motorola.models.resource.client.PushIncidentClientResource;
import com.motorola.models.resource.client.PushUnitClientResource;
import com.motorola.models.resource.client.ResponseNotificationClientResource;
import com.motorola.models.resource.client.ResponseUserSessionCorrelationIdClientResource;

/**
 * Entry-point for API calls.
 */
public class APIClient {

	private final Config config = new Config();

	/**
	 * Returns the SDK configuration.
	 */
	public Config getConfig() {
		return config;
	}

	public ResponseUserSessionCorrelationIdClientResource responseUserSessionCorrelationId(String correlationId) {
		return new ResponseUserSessionCorrelationIdClientResource(config, correlationId);
	}

	public ResponseNotificationClientResource responseNotification() {
		return new ResponseNotificationClientResource(config);
	}

	public PushIncidentClientResource pushIncident() {
		return new PushIncidentClientResource(config);
	}

	public PushUnitClientResource pushUnit() {
		return new PushUnitClientResource(config);
	}

}
