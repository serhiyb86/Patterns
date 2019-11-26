/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.manager;

import com.motorola.api.PushApi;
import com.motorola.api.utils.ApiException;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.UpdateEmergencyIncident;

/**
 * Class for executing Incident related requests.
 */
public class IncidentRequestManager extends BaseRequestManager {

	private final PushApi pushApi;

	public IncidentRequestManager() {
		this.pushApi = new PushApi(apiClient);
	}

	public ModelApiResponse createIncident(EmergencyIncident body) throws ApiException {
		return pushApi.createIncident(body, accessToken);
	}

	public ModelApiResponse updateIncident(UpdateEmergencyIncident body) throws ApiException {
		return pushApi.updateIncident(body, accessToken);
	}
}
