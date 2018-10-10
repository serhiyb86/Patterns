/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.cloud;

import com.motorola.models.Config;
import com.motorola.models.resource.client.IncidentIncidentKeyCommentClientResource;
import com.motorola.models.resource.client.IncidentIncidentKeyDispatchesDispatchKeyUnitClientResource;
import com.motorola.models.resource.client.IncidentKeysFindByCoverageAreasClientResource;
import com.motorola.models.resource.client.UnitStatusClientResource;
import com.motorola.models.resource.client.UnitUnitKeyClientResource;
import com.motorola.models.resource.client.UserSessionClientResource;
import com.motorola.models.resource.client.UserSessionSessionIdClientResource;
import com.motorola.models.resource.client.UserSessionSessionIdMonitorAreasClientResource;

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

	public IncidentIncidentKeyCommentClientResource incidentIncidentKeyComment(String incidentKey) {
		return new IncidentIncidentKeyCommentClientResource(config, incidentKey);
	}

	public IncidentIncidentKeyDispatchesDispatchKeyUnitClientResource incidentIncidentKeyDispatchesDispatchKeyUnit(String incidentKey, String dispatchKey) {
		return new IncidentIncidentKeyDispatchesDispatchKeyUnitClientResource(config, incidentKey, dispatchKey);
	}

	public IncidentKeysFindByCoverageAreasClientResource incidentKeysFindByCoverageAreas() {
		return new IncidentKeysFindByCoverageAreasClientResource(config);
	}

	public UnitStatusClientResource unitStatus() {
		return new UnitStatusClientResource(config);
	}

	public UnitUnitKeyClientResource unitUnitKey(String unitKey) {
		return new UnitUnitKeyClientResource(config, unitKey);
	}

	public UserSessionClientResource userSession() {
		return new UserSessionClientResource(config);
	}

	public UserSessionSessionIdClientResource userSessionSessionId(String sessionId) {
		return new UserSessionSessionIdClientResource(config, sessionId);
	}

	public UserSessionSessionIdMonitorAreasClientResource userSessionSessionIdMonitorAreas(String sessionId) {
		return new UserSessionSessionIdMonitorAreasClientResource(config, sessionId);
	}

}
