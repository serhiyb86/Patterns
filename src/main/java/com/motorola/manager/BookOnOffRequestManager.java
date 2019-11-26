/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.manager;

import com.motorola.api.ResponseApi;
import com.motorola.api.utils.ApiException;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.ResponseNotification;
import com.motorola.models.representation.UserSession;

/**
 * Class for executing BookOn/BookOff/ResponseNotification related requests.
 */
public class BookOnOffRequestManager extends BaseRequestManager {

	private final ResponseApi responseApi;

	public BookOnOffRequestManager() {
		this.responseApi = new ResponseApi(apiClient);
	}

	public ModelApiResponse bookOn(UserSession body, String correlationId) throws ApiException {
		return responseApi.bookOnResponse(body, accessToken, correlationId);
	}

	public ModelApiResponse bookOff(ResponseNotification body) throws ApiException {
		return responseApi.responseNotification(body, accessToken);
	}

	public ModelApiResponse responseNotification(ResponseNotification body) throws ApiException {
		return responseApi.responseNotification(body, accessToken);
	}

}
