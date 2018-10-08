/*
 * Copyright 2018 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.cloud;

import com.models.Config;
import com.models.resource.client.IncidentIncidentKeyCommentClientResource;
import com.models.resource.client.IncidentIncidentKeyDispatchesDispatchKeyUnitClientResource;
import com.models.resource.client.IncidentKeysFindByCoverageAreasClientResource;
import com.models.resource.client.UnitStatusClientResource;
import com.models.resource.client.UnitUnitKeyClientResource;
import com.models.resource.client.UserSessionClientResource;
import com.models.resource.client.UserSessionSessionIdClientResource;
import com.models.resource.client.UserSessionSessionIdMonitorAreasClientResource;

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
