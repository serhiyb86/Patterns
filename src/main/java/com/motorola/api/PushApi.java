/*
 * Copyright 2019 Motorola Solutions, Inc. ALL RIGHTS RESERVED
 */
package com.motorola.api;

import javax.ws.rs.core.GenericType;

import com.motorola.api.utils.ApiClient;
import com.motorola.api.utils.ApiException;
import com.motorola.api.utils.Configuration;
import com.motorola.api.utils.Pair;
import com.motorola.models.representation.EmergencyIncident;
import com.motorola.models.representation.ModelApiResponse;
import com.motorola.models.representation.Unit;
import com.motorola.models.representation.UpdateEmergencyIncident;
import com.motorola.models.representation.UpdateUnit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushApi {

	private ApiClient apiClient;

	public PushApi() {
		this(Configuration.getDefaultApiClient());
	}

	public PushApi(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	public ApiClient getApiClient() {
		return apiClient;
	}

	public void setApiClient(ApiClient apiClient) {
		this.apiClient = apiClient;
	}

	/**
	 * Receives new incidents from back end CAD systems
	 * Receives new incident
	 * @param body On Prem CAD system incident message (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse createIncident(EmergencyIncident body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling createIncident");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling createIncident");
		}
		// create path and map variables
		String localVarPath = "/push/incident";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Receives Off-duty unit status update request from back end CAD systems
	 * Receives Off-duty unit status update request
	 * @param authorization security token for authorization (required)
	 * @param customerId customer ID of the Unit (required)
	 * @param unitKey On Prem CAD system Off-duty unit key (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse offDutyUnit(String authorization, String customerId, String unitKey) throws ApiException {
		Object localVarPostBody = null;
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling offDutyUnit");
		}
		// verify the required parameter 'customerId' is set
		if (customerId == null) {
			throw new ApiException(400, "Missing the required parameter 'customerId' when calling offDutyUnit");
		}
		// verify the required parameter 'unitKey' is set
		if (unitKey == null) {
			throw new ApiException(400, "Missing the required parameter 'unitKey' when calling offDutyUnit");
		}
		// create path and map variables
		String localVarPath = "/push/unit/{unitKey}"
			.replaceAll("\\{" + "unitKey" + "\\}", apiClient.escapeString(unitKey.toString()));

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));
		if (customerId != null)
			localVarHeaderParams.put("customerId", apiClient.parameterToString(customerId));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {

		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Receives On-Duty request from back end CAD systems
	 * Receives new incident
	 * @param body On Prem CAD system Unit message (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse onDutyUnit(Unit body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling onDutyUnit");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling onDutyUnit");
		}
		// create path and map variables
		String localVarPath = "/push/unit";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Receives unit status update request from back end CAD systems
	 * Receives unit status update request
	 * @param body On Prem CAD system updated unit status message which contains two objects from/old and to/new Unit object (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse unitStatusUpdates(UpdateUnit body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling unitStatusUpdates");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling unitStatusUpdates");
		}
		// create path and map variables
		String localVarPath = "/push/unit";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}

	/**
	 * Receives incident updates from back end CAD systems
	 * Receives incident updates /unsolicited message
	 * @param body On Prem CAD system updated incident message which contains two objects from and to emergency incident object (required)
	 * @param authorization security token for authorization (required)
	 * @return ModelApiResponse
	 * @throws ApiException if fails to make API call
	 */
	public ModelApiResponse updateIncident(UpdateEmergencyIncident body, String authorization) throws ApiException {
		Object localVarPostBody = body;
		// verify the required parameter 'body' is set
		if (body == null) {
			throw new ApiException(400, "Missing the required parameter 'body' when calling updateIncident");
		}
		// verify the required parameter 'authorization' is set
		if (authorization == null) {
			throw new ApiException(400, "Missing the required parameter 'authorization' when calling updateIncident");
		}
		// create path and map variables
		String localVarPath = "/push/incident";

		// query params
		List<Pair> localVarQueryParams = new ArrayList<Pair>();
		Map<String, String> localVarHeaderParams = new HashMap<String, String>();
		Map<String, Object> localVarFormParams = new HashMap<String, Object>();

		if (authorization != null)
			localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));

		final String[] localVarAccepts = {
			"application/json"
		};
		final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

		final String[] localVarContentTypes = {
			"application/json"
		};
		final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

		String[] localVarAuthNames = new String[] { "clientCredentialApi_auth" };

		GenericType<ModelApiResponse> localVarReturnType = new GenericType<ModelApiResponse>() {

		};
		return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
	}
}
