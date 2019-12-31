/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.manager;

import com.motorola.api.PushApi;
import com.motorola.api.utils.ApiException;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.Unit;
import com.motorola.models.representation.UpdateUnit;


/**
 * Class for executing Unit related requests.
 */
public class UnitRequestManager extends BaseRequestManager {

	private final PushApi pushApi;

	public UnitRequestManager() {
		this.pushApi = new PushApi(apiClient);
	}

	public ModelApiResponse onDutyUnit(Unit body) throws ApiException {
		return pushApi.onDutyUnit(body, accessToken);
	}

	public ModelApiResponse unitStatusUpdates(UpdateUnit body) throws ApiException {
		return pushApi.unitStatusUpdates(body, accessToken);
	}

	public ModelApiResponse offDutyUnit(Unit body) throws ApiException {
		return pushApi.offDutyUnit(accessToken, body.getCustomerId(), body.getKey());
	}

}
