/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.manager;

import com.motorola.api.RefreshApi;
import com.motorola.api.utils.ApiException;
import com.motorola.models.representation.HistoricalIncidentApiResponse;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.RefreshIncidentData;
import com.motorola.models.representation.RefreshUnitData;

import java.util.List;

/**
 * Class for executing Bulk Updates related requests.
 */
public class BulkUpdateManager extends BaseRequestManager {

	private final RefreshApi refreshApi;

	public BulkUpdateManager() {
		this.refreshApi = new RefreshApi(apiClient);
	}

	public ModelApiResponse bulkIncidentUpdate(RefreshIncidentData body) throws ApiException {
		return refreshApi.refreshIncidents(body, accessToken);
	}

	public List<HistoricalIncidentApiResponse> bulkHistoricalIncidentUpdate(RefreshIncidentData body) throws ApiException {
		return refreshApi.refreshHistoricalIncidents(body, accessToken);
	}

	public ModelApiResponse bulkUnitUpdate(RefreshUnitData body) throws ApiException {
		return refreshApi.refreshUnits(body, accessToken);
	}

}
